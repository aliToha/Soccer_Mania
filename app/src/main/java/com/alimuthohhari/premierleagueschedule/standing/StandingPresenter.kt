package com.alimuthohhari.premierleagueschedule.standing

import com.alimuthohhari.premierleagueschedule.api.ApiInterface
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.helper.CoroutineContextProvider
import com.alimuthohhari.premierleagueschedule.helper.EspressoIdlingResource
import com.alimuthohhari.premierleagueschedule.model.League
import com.alimuthohhari.premierleagueschedule.model.Season
import com.alimuthohhari.premierleagueschedule.model.Standing
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StandingPresenter
    (
    private val view: StandingView,
    private val apiRepository: ApiRepo,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getLeagueList() {
        EspressoIdlingResource.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(ApiInterface.getLeague()).await(),
                    League::class.java
                )
            if(data == null){
                view.showLoading()
            }else{
                view.showLeagueList(data.leaguesList)
                view.hideLoading()
            }
        }
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }

    fun getStandingList(league: String, season: String) {
        EspressoIdlingResource.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(ApiInterface.getStanding(league, season)).await(),
                    Standing::class.java
                )
            if(data == null){
                view.showLoading()
            }else{
                view.showStandingList(data.standingList)
                view.hideLoading()
            }
        }
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }

    fun getSeasonList(league: String) {
        EspressoIdlingResource.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(ApiInterface.getSeason(league)).await(),
                    Season::class.java
                )
            if(data?.seasonsList == null){
                view.hideLoading()
                view.empty()
            }else{
                view.showSeasonList(data.seasonsList)
                view.hideLoading()
            }
        }
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }

}

