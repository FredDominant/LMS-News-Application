package com.noblemajesty.newsapplication

import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.viewmodels.NewsActivityViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit

import org.mockito.Mockito.*

class NewsActivityViewModelTest {

    @Rule @JvmField
    val mockitoRule = MockitoJUnit.rule()!!

    @Test
    fun viewModelField_newsShouldReturnCorrectValues(){
        val mockViewModel = mock(NewsActivityViewModel::class.java)
        val mockResponse = mock(NYTimesResponse::class.java)
        `when`(mockViewModel.news).thenReturn(mockResponse)
        assertEquals(mockViewModel.news, mockResponse)
        verify(mockViewModel, times(1)).news
        verify(mockViewModel, never()).sports
    }

    @Test
    fun viewModelField_sportsShouldReturnCorrectValues(){
        val mockViewModel = mock(NewsActivityViewModel::class.java)
        val mockResponse = mock(NYTimesResponse::class.java)
        `when`(mockViewModel.sports).thenReturn(mockResponse)
        assertEquals(mockViewModel.sports, mockResponse)
        verify(mockViewModel, times(1)).sports
        verify(mockViewModel, never()).news
    }

    @Test
    fun viewModelField_foodShouldReturnCorrectValues(){
        val mockViewModel = mock(NewsActivityViewModel::class.java)
        val mockResponse = mock(NYTimesResponse::class.java)
        `when`(mockViewModel.food).thenReturn(mockResponse)
        assertEquals(mockViewModel.food, mockResponse)
        verify(mockViewModel, times(1)).food
        verify(mockViewModel, never()).sports
    }

    @Test
    fun viewModelField_showShouldReturnCorrectValues(){
        val mockViewModel = mock(NewsActivityViewModel::class.java)
        `when`(mockViewModel.show).thenReturn(false)
        assertEquals(mockViewModel.show, false)
        verify(mockViewModel, times(1)).show
    }



    @Test
    fun fetchNews_ShouldWorkAsExpected() {
        val mockResponse = mock(NYTimesResponse::class.java)
        val mockViewModel = mock(NewsActivityViewModel::class.java)
        `when`(mockResponse.status).thenReturn("success")
        mockViewModel.fetchNews({
            assertEquals(it.results, "success")
            verify(mockResponse).results
        }, {})
    }

    @Test
    fun fetchSports_ShouldWorkAsExpected() {
        val mockResponse = mock(NYTimesResponse::class.java)
        val mockViewModel = mock(NewsActivityViewModel::class.java)
        `when`(mockResponse.status).thenReturn("success")
        mockViewModel.fetchSports({
            assertEquals(it.results, "success")
            verify(mockResponse).num_results
        }, {})
    }

    @Test
    fun fetchFood_ShouldWorkAsExpected() {
        val mockResponse = mock(NYTimesResponse::class.java)
        val mockViewModel = mock(NewsActivityViewModel::class.java)
        `when`(mockResponse.status).thenReturn("success")
        mockViewModel.fetchFood({
            assertEquals(it.results, "success")
            verify(mockResponse).status
        }, {
        })
    }

//    fun fetchNews_ShouldWorkHasExpected() {
//        val mockResponse = mock(NYTimesResponse::class.java)
//        val mockException = mock(Exception::class.java)
//        val mockViewModel = mock(NewsActivityViewModel::class.java)
//        `when`(mockViewModel.fetchNews({mockResponse}, {mockException})
//    }
}