package com.alimuthohhari.premierleagueschedule.search

import android.app.usage.UsageEvents
import com.alimuthohhari.premierleagueschedule.api.ApiInterface
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.helper.TestContextProvider
import com.alimuthohhari.premierleagueschedule.model.Events
import com.alimuthohhari.premierleagueschedule.model.ListEvents
import com.alimuthohhari.premierleagueschedule.model.Players
import com.alimuthohhari.premierleagueschedule.model.Team
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchPresenterTest3 {
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
    fun testGetPlayerList() {

        val playerList : MutableList<Players.PlayerList> = mutableListOf()
        val response = Players(playerList)
        val textSearch = "pogba"

        `when`(
            gson.fromJson(
                apiRepository
                    .doRequest(ApiInterface.getPlayers(textSearch)).getCompleted(),
                Players::class.java
            )
        ).thenReturn(response)

        presentr.getPlayerList(textSearch)

        verify(view).showLoading()
        verify(view).showPlayer(playerList)
        verify(view).hideLoading()
    }
}