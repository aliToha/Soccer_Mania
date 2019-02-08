package com.alimuthohhari.premierleagueschedule.search

import com.alimuthohhari.premierleagueschedule.api.ApiInterface
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.helper.CoroutineContextProvider
import com.alimuthohhari.premierleagueschedule.helper.EspressoIdlingResource
import com.alimuthohhari.premierleagueschedule.model.EventsSearch
import com.alimuthohhari.premierleagueschedule.model.Players
import com.alimuthohhari.premierleagueschedule.model.Team
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchPresenter(
    private val view: FindView,
    private val apiRepository: ApiRepo,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getMatchList(search: String) {
        EspressoIdlingResource.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(ApiInterface.getMatch(search)).await(),
                EventsSearch::class.java
            )
            if(data?.listEvents == null){
                view.showLoading()
            }else{
                view.hideLoading()
                view.showMatch(data.listEvents)
            }
        }
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }

    fun getTeamList(search: String) {
        EspressoIdlingResource.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(ApiInterface.getClub(search)).await(),
                Team::class.java
            )
            if(data?.listTeam == null){
                view.showLoading()
            }else{
                view.showTeam(data.listTeam)
                view.hideLoading()
            }
        }
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }

    fun getPlayerList(search: String) {
        EspressoIdlingResource.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(ApiInterface.getPlayers(search)).await(),
                Players::class.java
            )
            if(data?.playerList == null){
                view.showLoading()
            }else{
                view.hideLoading()
                view.showPlayer(data.playerList)
            }

        }
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }
}