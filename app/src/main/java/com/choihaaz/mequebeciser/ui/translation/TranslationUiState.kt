package com.choihaaz.mequebeciser.ui.translation

data class TranslationUiState(
    val isLoading: Boolean = false,
    val translatedText: String = "",
    val error: String? = null,
)
