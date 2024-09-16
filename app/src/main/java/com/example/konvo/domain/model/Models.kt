package com.example.konvo.domain.model

import com.google.gson.annotations.SerializedName

// region Assistant
data class AssistantRequest(
    val instructions: String,
    val name: String,
    val model: String = "gpt-4o-mini",
    val tools: List<Tool>? = null,
)

data class Tool(
    val type: String
)

data class AssistantResponse(
    val id: String,
    @SerializedName("object")
    val assistantObject: String,
    @SerializedName("created_at")
    val createdAt: Long,
    val name: String,
    val description: String?,
    val model: String,
    val instructions: String,
    val tools: List<Tool>? = null,
    @SerializedName("top_p") val topP: Double,
    val temperature: Double,
    val metadata: Map<String, Any>,
    @SerializedName("response_format") val responseFormat: String
)
// endregion

// region Threads
data class ThreadResponse(
    val id: String,
    @SerializedName("object")
    val threadObject: String,
    @SerializedName("created_at")
    val createdAt: Long,
    val metadata: Map<String, Any>,
    @SerializedName("tool_resources")
    val toolResources: Map<String, Any>
)
//endregion

// region Run
data class RunRequest(
    @SerializedName("assistant_id")
    val assistantID: String
)

data class RunResponse(
    @SerializedName("id") val id: String,
    @SerializedName("object") val runObject: String,
    @SerializedName("created_at") val createdAt: Long,
    @SerializedName("assistant_id") val assistantId: String,
    @SerializedName("thread_id") val threadId: String,
    val status: String,
    @SerializedName("started_at") val startedAt: Long?,
    @SerializedName("expires_at") val expiresAt: Long,
    @SerializedName("cancelled_at") val cancelledAt: Long?,
    @SerializedName("failed_at") val failedAt: Long?,
    @SerializedName("completed_at") val completedAt: Long?,
    @SerializedName("required_action") val requiredAction: Any?,
    @SerializedName("last_error") val lastError: Any?,
    val model: String,
    val instructions: String,
    val tools: List<Tool>,
    @SerializedName("tool_resources") val toolResources: Map<String, Any>,
    val metadata: Map<String, Any>,
    val temperature: Double,
    @SerializedName("top_p") val topP: Double,
    @SerializedName("max_completion_tokens") val maxCompletionTokens: Int?,
    @SerializedName("max_prompt_tokens") val maxPromptTokens: Int?,
    @SerializedName("truncation_strategy") val truncationStrategy: TruncationStrategy,
    @SerializedName("incomplete_details") val incompleteDetails: Any?,
    val usage: Any?,
    @SerializedName("response_format") val responseFormat: String,
    @SerializedName("tool_choice") val toolChoice: String,
    @SerializedName("parallel_tool_calls") val parallelToolCalls: Boolean
)

data class TruncationStrategy(
    val type: String,
    @SerializedName("last_messages")
    val lastMessages: Int?
)
// endregion

// region Message
// Chat
data class ChatMessage(
    val role: String,
    val content: String
)

// Add
data class MessageResponse(
    val id: String,
    @SerializedName("object") val outputObject: String,
    @SerializedName("created_at") val createdAt: Long,
    @SerializedName("assistant_id") val assistantId: String?,
    @SerializedName("thread_id") val threadId: String,
    @SerializedName("run_id") val runId: String?,
    val role: String,
    val content: List<Content>,
    val attachments: List<Any>,
    val metadata: Map<String, Any>
)

data class Content(
    val type: String,
    val text: Text
)

data class Text(
    val value: String,
    val annotations: List<Any>
)

// List
data class MessageListResponse(
    @SerializedName("object") val messageListObject: String? = null,
    val data: List<MessageResponse> = emptyList(),
    @SerializedName("first_id") val firstID: String? = null,
    @SerializedName("last_id") val lastID: String? = null,
    @SerializedName("has_more") val hasMore: Boolean? = null,
)

// endregion
