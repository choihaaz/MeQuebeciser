
package com.choihaaz.mequebeciser.ui.translation

import com.choihaaz.mequebeciser.domain.repository.TranslationRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TranslationViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val mockRepository = mockk<TranslationRepository>()
    private lateinit var viewModel: TranslationViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TranslationViewModel(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `translate success should update uiState correctly`() = runTest {
        // Arrange
        val inputText = "안녕하세요"
        val responseFlow = flowOf("Hello - Bonjour")
        val expectedText = "Hello - Bonjour"

        coEvery { mockRepository.getTranslationStream(inputText) } returns responseFlow

        // Act
        viewModel.translate(inputText)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val finalState = viewModel.uiState.value
        assertEquals(expectedText, finalState.translatedText)
        assertEquals(false, finalState.isLoading)
        assertEquals(null, finalState.error)
    }
}