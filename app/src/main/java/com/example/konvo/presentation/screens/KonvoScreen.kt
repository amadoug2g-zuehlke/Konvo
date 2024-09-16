package com.example.konvo.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.konvo.components.ChatBubble
import com.example.konvo.data.remote.RetrofitInstance
import com.example.konvo.data.repository.KonvoRepository
import com.example.konvo.presentation.viewmodel.KonvoViewModel
import com.example.konvo.presentation.viewmodel.KonvoViewModelFactory

@Composable
fun KonvoScreen(
    modifier: Modifier = Modifier,
    viewModel: KonvoViewModel = viewModel(
        factory = KonvoViewModelFactory(
            KonvoRepository(
                RetrofitInstance.openAIService
            )
        )
    )
) {
    val konversation by viewModel.messageList.collectAsState()
    val message by viewModel.userInput.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
//         Chat history display
        LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = true
        ) {
            items(konversation.data!!.size) { message ->
                val lastMessage = konversation.data!![message]

                ChatBubble(
                    message = lastMessage.content.last().text.value,
                    isUser = lastMessage.role == "user" // Align user messages on the right
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // User input and send button
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = message,
                onValueChange = { viewModel.onUserInputChanged(it) },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type your message...") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { viewModel.addUserMessage() },
                enabled = message.isNotEmpty()
            ) {
                Text("Send")
            }
        }
    }
}

