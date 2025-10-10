package com.choihaaz.mequebeciser.di

import com.choihaaz.mequebeciser.data.remote.AIRemoteDataSource
import com.choihaaz.mequebeciser.data.remote.AIRemoteDataSourceImpl
import com.choihaaz.mequebeciser.data.repository.TranslationRepositoryImpl
import com.choihaaz.mequebeciser.domain.repository.TranslationRepository
import com.choihaaz.mequebeciser.ui.translation.TranslationViewModel
import com.google.firebase.Firebase
import com.google.firebase.ai.GenerativeModel
import com.google.firebase.ai.ai
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<GenerativeModel> {
        Firebase
            .ai()
            .generativeModel(modelName = "gemini-2.5-flash")
    }

    single<AIRemoteDataSource> {
        AIRemoteDataSourceImpl(generativeModel = get())
    }

    single<TranslationRepository> {
        TranslationRepositoryImpl(aiRemoteDataSource = get())
    }

    viewModel {
        TranslationViewModel(translationRepository = get())
    }
}