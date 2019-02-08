package com.alimuthohhari.premierleagueschedule.search

import android.app.usage.UsageEvents
import com.alimuthohhari.premierleagueschedule.api.ApiInterface
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.helper.TestContextProvider
import com.alimuthohhari.premierleagueschedule.model.*
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchPresenterTest {
    private lateinit var presentr:SearchPresenter

    @Mock
    private
    lateinit var view: FindView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepo

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presentr = SearchPresenter(
            view, apiRepository, gson,
            TestContextProvider()
        )
    }


    @Test
    fun tesGetMatchList() {
        val matchList : MutableList<ListEvents> = mutableListOf()
        val response = EventsSearch(matchList)
        val textSearch = "Arsenal"

        `when`(
            gson.fromJson(
                apiRepository
                    .doRequest(ApiInterface.getMatch(textSearch)).getCompleted(),
                EventsSearch::class.java
            )
        ).thenReturn(response)

        presentr.getMatchList(textSearch)

        verify(view).showLoading()
        verify(view).showMatch(matchList)
        verify(view).hideLoading()
    }

}