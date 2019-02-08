package com.alimuthohhari.premierleagueschedule.schedule

import com.alimuthohhari.premierleagueschedule.api.ApiInterface
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.helper.CoroutineContextProvider
import com.alimuthohhari.premierleagueschedule.helper.EspressoIdlingResource
import com.alimuthohhari.premierleagueschedule.model.League
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchPresenter (
    private val view: MatchView,
    private val apiRepository: ApiRepo,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    fun getLeague() {
        EspressoIdlingResource.increment()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(ApiInterface.getLeague()).await(),
                    League::class.java
                )
                view.showLeague(data.leaguesList)

        }
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }
}