package com.alimuthohhari.premierleagueschedule.presenter

import com.alimuthohhari.premierleagueschedule.api.ApiInterface
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.helper.TestContextProvider
import com.alimuthohhari.premierleagueschedule.model.Standing
import com.alimuthohhari.premierleagueschedule.standing.StandingPresenter
import com.alimuthohhari.premierleagueschedule.standing.StandingView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class StandingPresenterTest {
    private lateinit var presentr: StandingPresenter

    @Mock
    private
    lateinit var view: StandingView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepo

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presentr = StandingPresenter(
            view, apiRepository, gson,
            TestContextProvider()
        )
    }

    @Test
    fun testGetStandingList() {
        val listStanding: MutableList<Standing.StandingList> = mutableListOf()
        val response = Standing(listStanding)
        val league = "4328" //id league
        val season = "1819" //id season

        GlobalScope.launch {
        `when`(
            gson.fromJson(
                apiRepository
                    .doRequest(ApiInterface.getStanding(league,season)).await(),
                Standing::class.java
            )
        ).thenReturn(response)

        presentr.getStandingList(league,season)

        verify(view).showLoading()
        verify(view).showStandingList(listStanding)
        verify(view).hideLoading()
        }
    }
}