package com.choihaaz.mequebeciser.data.repository

import com.choihaaz.mequebeciser.data.remote.AIRemoteDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class TranslationRepositoryImplTest {

    private val aiRemoteDataSource = mockk<AIRemoteDataSource>()
    private val repository = TranslationRepositoryImpl(aiRemoteDataSource)

    @Test
    fun `repository returns translation stream from remote data source`() = runTest {
        // Arrange
        val textToTranslate = "안녕하세요"
        val expectedResponse = "Hello - Bonjour"
        coEvery { aiRemoteDataSource.getTranslationStream(textToTranslate) } returns flowOf(expectedResponse)

        // Act
        val result = repository.getTranslationStream(textToTranslate)
        val actualResponse = result.first()

        // Assert
        assertEquals(expectedResponse, actualResponse)
    }
}