package com.alimuthohhari.premierleagueschedule.club

import com.alimuthohhari.premierleagueschedule.api.ApiInterface
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.helper.CoroutineContextProvider
import com.alimuthohhari.premierleagueschedule.helper.EspressoIdlingResource
import com.alimuthohhari.premierleagueschedule.model.Players
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailClubPresenter(
    private val view: DetailClubView,
    private val apiRepository: ApiRepo,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getPlayers(teamId: String) {
        EspressoIdlingResource.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(ApiInterface.getAllPlayers(teamId)).await(),
                    Players::class.java
                )
            if(data?.playerList == null){
                view.showLoading()
                view.empty()
            }else{
                view.showPlayer(data.playerList)
                view.hideLoading()
            }
        }
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }

}