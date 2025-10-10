package com.choihaaz.mequebeciser.data.repository

import com.choihaaz.mequebeciser.data.remote.AIRemoteDataSource
import com.choihaaz.mequebeciser.domain.repository.TranslationRepository
import kotlinx.coroutines.flow.Flow

class TranslationRepositoryImpl(
    private val aiRemoteDataSource: AIRemoteDataSource
) : TranslationRepository {
    override fun getTranslationStream(textToTranslate: String): Flow<String> {
        return aiRemoteDataSource.getTranslationStream(textToTranslate)
    }
}