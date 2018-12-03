package com.noblemajesty.newsapplication

import com.noblemajesty.newsapplication.utils.NetworkConnectivity
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit

class NetworkConnectivityClassTest {

    @Rule
    @JvmField
    val mockitoRule = MockitoJUnit.rule()!!

    @Test
    fun networkConnectivityClass_shouldBehaveAsExpected() {
        val mockClass = mock(NetworkConnectivity::class.java)
        `when`(mockClass.isConnected()).thenReturn(false)
        mockClass.isConnected()
        mockClass.isConnected()

        verify(mockClass, times(2)).isConnected()
    }
}