package com.choihaaz.mequebeciser.data.remote

import kotlinx.coroutines.flow.Flow

interface AIRemoteDataSource {
    fun getTranslationStream(textToTranslate: String): Flow<String>
}