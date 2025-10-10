package com.choihaaz.mequebeciser.data.remote

import com.google.firebase.ai.GenerativeModel
import com.google.firebase.ai.type.GenerateContentResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AIRemoteDataSourceImpl(
    private val generativeModel: GenerativeModel
) : AIRemoteDataSource {
    override fun getTranslationStream(textToTranslate: String): Flow<String> {
        val prompt = """
    As a translator for a Korean learner, create 3 varied sentences (formal, casual, idiomatic) for the given Korean text.
Format: 1. [Nuance] English - Quebec French

[EXAMPLE]
Korean: "피곤해요"
Output:

[Formal] I am feeling quite fatigued. - Je ressens une grande fatigue.

[Casual] I'm tired. - Je suis fatigué.

[Idiomatic] I'm wiped out. - Je suis brûlé.

[TASK]
Korean: "$textToTranslate"
Output:
""".trimIndent()
        val responseStream: Flow<GenerateContentResponse> = generativeModel.generateContentStream(prompt)

        return responseStream.map { response ->
            response.text ?: ""
        }
    }
}