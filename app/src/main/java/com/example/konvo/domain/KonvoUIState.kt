package com.example.konvo.domain

import com.example.konvo.domain.model.ThreadResponse

data class KonvoUIState(
    val thread: ThreadResponse,
    val selectedScenario: String = "",
    val selectedLanguage: String = "",
    val message: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)