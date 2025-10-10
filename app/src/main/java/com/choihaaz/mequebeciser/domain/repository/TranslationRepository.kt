package com.choihaaz.mequebeciser.domain.repository

import kotlinx.coroutines.flow.Flow

interface TranslationRepository {

    /**
     * Retrieves a translation for the given text.
     *
     * This function communicate with the AI model to translate the input text.
     * The translation is returned as a Flow, allowing the text to be displayed
     * progressively as it's being generated, creating a real-time typing effect.
     *
     * @param textToTranslate The korean text to be translated.
     * @return A [Flow] of Strings, where each String is a piece of the generated translation.
     *
     */
    fun getTranslationStream(textToTranslate: String): Flow<String>
}