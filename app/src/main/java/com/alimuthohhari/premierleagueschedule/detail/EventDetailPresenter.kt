package com.alimuthohhari.premierleagueschedule.detail

import com.alimuthohhari.premierleagueschedule.api.ApiInterface
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.helper.CoroutineContextProvider
import com.alimuthohhari.premierleagueschedule.helper.EspressoIdlingResource
import com.alimuthohhari.premierleagueschedule.model.Events
import com.alimuthohhari.premierleagueschedule.model.Team
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EventDetailPresenter
    (
    private val view: DetailView,
    private val apiRepository: ApiRepo,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {


    fun getBadgeHome(idHome: String?) {
        EspressoIdlingResource.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(ApiInterface.getBadge(idHome)).await(),
                Team::class.java
            )
            if(data == null){
                view.showLoading()
            }else{
                view.showHomeBadge(data.listTeam)
                view.hideLoading()
            }
        }
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }

    fun getBadgeAway(idAway: String?) {
        EspressoIdlingResource.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(ApiInterface.getBadge(idAway)).await(),
                Team::class.java
            )
            view.showAwayBadge(data.listTeam)
            view.hideLoading()
        }
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }

    fun getDetail(idEvent: String?) {
        EspressoIdlingResource.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(ApiInterface.getDetail(idEvent)).await(),
                Events::class.java
            )
            view.showDetail(data.listEvents!!)
            view.hideLoading()
        }
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }
}
