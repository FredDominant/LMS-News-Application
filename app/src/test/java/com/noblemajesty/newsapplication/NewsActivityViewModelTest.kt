package com.noblemajesty.newsapplication

import android.content.ContentValues
import android.net.Uri
import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.models.Result
import com.noblemajesty.newsapplication.viewmodels.NewsActivityViewModel
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.junit.Assert.assertEquals

class NewsActivityViewModelTest {

    @Rule @JvmField
    val mockitoRule = MockitoJUnit.rule()!!
    private val viewModel = mock(NewsActivityViewModel::class.java)!!


    @Test
    fun viewModel_shouldClearDisposable() {
        viewModel.clearDisposable()
        verify(viewModel).clearDisposable()
        verify(viewModel, times(1)).clearDisposable()
    }

    @Test
    fun viewModel_shouldSaveWithContentProvider() {
        val uri = mock(Uri::class.java)
        val contentValues = mock(ContentValues::class.java)
        viewModel.saveWithContentProvider(uri, contentValues)
        verify(viewModel).saveWithContentProvider(uri, contentValues)
        verify(viewModel, times(1)).saveWithContentProvider(uri, contentValues)
    }

    @Test
    fun saveWithContentProvider() {
        val data = listOf(mock(Result::class.java), mock(Result::class.java))
        viewModel.saveNewsToDBWithContentProvider(data, "")
        verify(viewModel, atLeast(1))
                .saveNewsToDBWithContentProvider(data, "")
    }

    @Test
    fun getNews_shouldWorkAsExpected() {
        val mockObservable = mock(NYTimesResponse::class.java)
        val observableResponse = Observable.just(mockObservable)
        `when`(viewModel.getNews("")).thenReturn(observableResponse)
        viewModel.getNews("")
        assertEquals(viewModel.getNews(""), observableResponse)
        verify(viewModel, times(2)).getNews("")
    }
}