package com.example.konvo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.konvo.data.repository.KonvoRepository
import com.example.konvo.domain.model.ChatMessage
import com.example.konvo.domain.model.MessageListResponse
import com.example.konvo.domain.model.RunRequest
import com.example.konvo.utils.Constants.ASST_ID
import com.example.konvo.utils.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class KonvoViewModel(private val repository: KonvoRepository) : ViewModel() {
    private val _messageList = MutableStateFlow(MessageListResponse())
    val messageList = _messageList.asStateFlow()

    private val _userInput = MutableStateFlow("")
    val userInput = _userInput.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    private var threadId: String = "thread_DaVcZOu3D4QZ8VeGqSqLeOxL"

    // Step 1: Create a new thread
    private fun createThread() {
        viewModelScope.launch {
            repository.createThread().collect { state ->
                when (state) {
                    is State.Loading -> startLoading()

                    is State.Success -> {
                        threadId = state.data.id
                        onResult()
                    }

                    is State.Failed -> onResult(state.message)
                }
            }
        }
    }

    // Step 2: Add user input as a message to the thread
    fun addUserMessage() {
        threadId.let { id ->
            viewModelScope.launch {
                val chatMessage = ChatMessage("user", _userInput.value)
                repository.addMessage(id, chatMessage).collect { state ->
                    when (state) {
                        is State.Loading -> startLoading()

                        is State.Success -> {
                            Log.d("KVM", "add: ${state.data}")
                            runAssistant() // Automatically run the assistant after user message
                            onResult()
                        }

                        is State.Failed -> onResult(state.message)
                    }
                }
            }
        }
    }

    // Step 3: Run the assistant on the thread after user message
    private fun runAssistant() {
        viewModelScope.launch {
            repository.runAssistant(threadId, RunRequest(ASST_ID)).collect { state ->
                when (state) {
                    is State.Loading -> startLoading()

                    is State.Success -> {
                        monitorMessages(threadId) // Monitor messages for AI response
                        Log.d("KVM", "run: ${state.data}")
                        onResult()
                    }

                    is State.Failed -> onResult(state.message)
                }
            }
        }
    }

    // Step 4: Constantly monitor the messages to get AI responses
    private fun monitorMessages(threadId: String) {
        viewModelScope.launch {
            repository.getMessages(threadId).collect { state ->
                when (state) {
                    is State.Loading -> startLoading()

                    is State.Success -> {
                        _messageList.value = state.data
                        Log.d("KVM", "monitor: ${state.data}")
                        onResult()
                    }

                    is State.Failed -> onResult(state.message)
                }
            }
        }
    }

    // Update the user input state
    fun onUserInputChanged(input: String) {
        _userInput.value = input
    }

    // Additional helper functions for UI can go here
    private fun startLoading() {
        _loadingState.value = true
    }

    private fun stopLoading() {
        _loadingState.value = false
    }

    private fun onResult(message: String = "") {
        _errorMessage.value = message
        Log.d("KVM", "onResult: $message")
        stopLoading()
        resetErrorMessages()
    }

    private fun resetErrorMessages() {
        _errorMessage.value = ""
    }
}


class KonvoViewModelFactory(private val repository: KonvoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KonvoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return KonvoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}