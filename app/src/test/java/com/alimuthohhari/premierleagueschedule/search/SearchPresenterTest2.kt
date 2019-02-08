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

class SearchPresenterTest2 {
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
    fun testGetTeamList() {
        val teamList : MutableList<Team.TeamList> = mutableListOf()
        val response = Team(teamList)
        val textSearch = "Arsenal"

        `when`(
            gson.fromJson(
                apiRepository
                    .doRequest(ApiInterface.getClub(textSearch)).getCompleted(),
                Team::class.java
            )
        ).thenReturn(response)

        presentr.getTeamList(textSearch)

        verify(view).showLoading()
        verify(view).showTeam(teamList)
        verify(view).hideLoading()
    }

}