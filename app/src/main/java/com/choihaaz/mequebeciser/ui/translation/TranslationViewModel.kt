package com.choihaaz.mequebeciser.ui.translation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.choihaaz.mequebeciser.domain.repository.TranslationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TranslationViewModel(
    private val translationRepository: TranslationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TranslationUiState())
    val uiState: StateFlow<TranslationUiState> = _uiState.asStateFlow()

    fun translate(text: String) {
        viewModelScope.launch {
            translationRepository.getTranslationStream(text)
                .onStart {
                    _uiState.update { it.copy(isLoading = true) }
                }
                .catch { e ->
                    _uiState.update { it.copy(error = e.message) }
                }
                .onCompletion {
                    _uiState.update { it.copy(isLoading = false) }
                }
                .collect { newTextStream ->
                    _uiState.update { it.copy(translatedText = it.translatedText + newTextStream) }
                }
        }
    }
}