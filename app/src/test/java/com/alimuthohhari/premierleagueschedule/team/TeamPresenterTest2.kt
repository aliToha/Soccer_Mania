package com.alimuthohhari.premierleagueschedule.team

import com.alimuthohhari.premierleagueschedule.api.ApiInterface
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.helper.TestContextProvider
import com.alimuthohhari.premierleagueschedule.model.League
import com.alimuthohhari.premierleagueschedule.model.Team
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class TeamPresenterTest2 {
    private lateinit var presentr: TeamPresenter

    @Mock
    private
    lateinit var view: TeamView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepo

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presentr = TeamPresenter(
            view, apiRepository, gson,
            TestContextProvider()
        )
    }

    @Test
    fun testGetTeamList() {
        val listTeam: MutableList<Team.TeamList> = mutableListOf()
        val response = Team(listTeam)
        val league = "4328" //id league

        `when`(
            gson.fromJson(
                apiRepository
                    .doRequest(ApiInterface.getAllTeam(league)).await(),
                Team::class.java
            )
        ).thenReturn(response)

        presentr.getTeamList(league)

        verify(view).hideLoading()
        verify(view).showTeamList(listTeam)
        verify(view).showLoading()
    }

}