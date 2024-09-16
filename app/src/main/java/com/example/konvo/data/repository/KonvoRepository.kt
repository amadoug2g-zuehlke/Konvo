package com.example.konvo.data.repository

import com.example.konvo.data.remote.OpenAIApi
import com.example.konvo.domain.model.ChatMessage
import com.example.konvo.domain.model.RunRequest
import com.example.konvo.domain.model.ThreadResponse
import com.example.konvo.utils.State
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class KonvoRepository(private val api: OpenAIApi) {
    fun createThread()= flow {
        emit(State.loading())
        val result = api.createThread()

        emit(State.success(result))
    }.catch {
        emit(State.failed(it.localizedMessage?.toString() ?: it.message.toString()))
    }.flowOn(IO)

    fun runAssistant(threadId: String, runRequest: RunRequest) = flow {
        emit(State.loading())
        val result = api.runAssistant(threadId, runRequest)

        emit(State.success(result))
    }.catch {
        emit(State.failed(it.localizedMessage?.toString() ?: it.message.toString()))
    }.flowOn(IO)

    fun addMessage(threadId: String, chatMessage: ChatMessage) = flow {
        emit(State.loading())
        val result = api.addMessage(threadId, chatMessage)

        emit(State.success(result))
    }.catch {
        emit(State.failed(it.localizedMessage?.toString() ?: it.message.toString()))
    }.flowOn(IO)

    fun getMessages(threadId: String) = flow {
        emit(State.loading())
        val result = api.getMessages(threadId)

        emit(State.success(result))
    }.catch {
        emit(State.failed(it.localizedMessage?.toString() ?: it.message.toString()))
    }.flowOn(IO)
}