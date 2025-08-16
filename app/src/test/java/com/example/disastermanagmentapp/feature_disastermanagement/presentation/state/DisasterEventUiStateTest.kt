package com.example.disastermanagmentapp.feature_disastermanagement.presentation.state

import com.example.disastermanagmentapp.feature_disastermanagement.domain.model.DisasterEvent
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DisasterEventUiStateTest {

    @Test
    fun `DisasterEventListState has correct default values`() {
        // When
        val state = DisasterEventListState()

        // Then
        assertTrue(state.uiState is DisasterEventUiState.Loading)
        assertEquals("", state.searchQuery)
        assertEquals(null, state.selectedCategory)
        assertFalse(state.isRefreshing)
    }

    @Test
    fun `DisasterEventListState copy works correctly`() {
        // Given
        val originalState = DisasterEventListState()
        val events = listOf(
            DisasterEvent(
                id = "1",
                title = "Test Disaster",
                description = "Test Description",
                link = "https://test.com",
                categories = emptyList(),
                geometries = emptyList()
            )
        )

        // When
        val updatedState = originalState.copy(
            uiState = DisasterEventUiState.Success(events),
            searchQuery = "earthquake",
            selectedCategory = "severe-storms"
        )

        // Then
        assertTrue(updatedState.uiState is DisasterEventUiState.Success)
        assertEquals("earthquake", updatedState.searchQuery)
        assertEquals("severe-storms", updatedState.selectedCategory)
        assertFalse(updatedState.isRefreshing)
    }

    @Test
    fun `DisasterEventUiState Success contains correct events`() {
        // Given
        val events = listOf(
            DisasterEvent(
                id = "1",
                title = "Test Disaster",
                description = "Test Description",
                link = "https://test.com",
                categories = emptyList(),
                geometries = emptyList()
            )
        )

        // When
        val successState = DisasterEventUiState.Success(events)

        // Then
        assertEquals(events, successState.events)
    }

    @Test
    fun `DisasterEventUiState Error contains correct message`() {
        // Given
        val errorMessage = "Network error occurred"

        // When
        val errorState = DisasterEventUiState.Error(errorMessage)

        // Then
        assertEquals(errorMessage, errorState.message)
    }
}
