package com.example.konvo.data.remote

import com.example.konvo.domain.model.AssistantRequest
import com.example.konvo.domain.model.AssistantResponse
import com.example.konvo.domain.model.ChatMessage
import com.example.konvo.domain.model.MessageListResponse
import com.example.konvo.domain.model.MessageResponse
import com.example.konvo.domain.model.RunRequest
import com.example.konvo.domain.model.RunResponse
import com.example.konvo.domain.model.ThreadResponse
import com.example.konvo.utils.State
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OpenAIApi {
    @Deprecated("Custom Assistant to be implemented")
    @POST("v1/assistants")
    fun createAssistant(@Body assistantRequest: AssistantRequest): Response<AssistantResponse>

    @POST("v1/threads")
    suspend fun createThread(): ThreadResponse

    @POST("v1/threads/{thread_id}/runs")
    suspend fun runAssistant(
        @Path("thread_id") threadId: String,
        @Body runRequest: RunRequest
    ): RunResponse

    @POST("v1/threads/{thread_id}/messages")
    suspend fun addMessage(
        @Path("thread_id") threadId: String,
        @Body chatMessage: ChatMessage
    ): MessageResponse

    @GET("v1/threads/{thread_id}/messages")
    suspend fun getMessages(@Path("thread_id") threadId: String): MessageListResponse
}