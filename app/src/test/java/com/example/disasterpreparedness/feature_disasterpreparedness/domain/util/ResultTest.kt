package com.example.disasterpreparedness.feature_disasterpreparedness.domain.util

import com.example.disasterpreparedness.feature_eonet_api.domain.util.Result
import org.junit.Assert.*
import org.junit.Test

class ResultTest {

    @Test
    fun `Success result properties work correctly`() {
        val data = "test data"
        val result = Result.Success(data)
        
        assertTrue(result.isSuccess())
        assertFalse(result.isError())
        assertFalse(result.isLoading())
        assertEquals(data, result.getOrNull())
        assertNull(result.exceptionOrNull())
    }

    @Test
    fun `Error result properties work correctly`() {
        val exception = Exception("test error")
        val result = Result.Error(exception)
        
        assertFalse(result.isSuccess())
        assertTrue(result.isError())
        assertFalse(result.isLoading())
        assertNull(result.getOrNull())
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `Loading result properties work correctly`() {
        val result = Result.Loading
        
        assertFalse(result.isSuccess())
        assertFalse(result.isError())
        assertTrue(result.isLoading())
        assertNull(result.getOrNull())
        assertNull(result.exceptionOrNull())
    }
}
