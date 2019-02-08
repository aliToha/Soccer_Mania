package com.alimuthohhari.premierleagueschedule.team

import com.alimuthohhari.premierleagueschedule.api.ApiInterface
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.helper.CoroutineContextProvider
import com.alimuthohhari.premierleagueschedule.helper.EspressoIdlingResource
import com.alimuthohhari.premierleagueschedule.model.League
import com.alimuthohhari.premierleagueschedule.model.Team
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(
    private val view: TeamView,
    private val apiRepository: ApiRepo,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getLeague() {
        EspressoIdlingResource.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(ApiInterface.getLeague()).await(),
                    League::class.java
                )
                view.showLeague(data.leaguesList)
                view.hideLoading()
        }
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }

    fun getTeamList(idLeague: String) {
        EspressoIdlingResource.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(ApiInterface.getAllTeam(idLeague)).await(),
                Team::class.java
            )
            if(data?.listTeam == null){
                view.hideLoading()
                view.empty()
            }else{
                view.showTeamList(data.listTeam)
                view.hideLoading()
            }
        }
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }

}