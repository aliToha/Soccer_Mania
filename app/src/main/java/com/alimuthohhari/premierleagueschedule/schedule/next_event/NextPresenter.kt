package com.alimuthohhari.premierleagueschedule.schedule.next_event

import com.alimuthohhari.premierleagueschedule.api.ApiInterface
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.helper.CoroutineContextProvider
import com.alimuthohhari.premierleagueschedule.helper.EspressoIdlingResource
import com.alimuthohhari.premierleagueschedule.model.Events
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NextPresenter(
    private val view: NextView,
    private val apiRepository: ApiRepo,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getEventNext(idLeague: String?) {
        EspressoIdlingResource.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(ApiInterface.getEventsNext(idLeague)).await(),
                    Events::class.java
                )
            if(data?.listEvents == null){
                view.hideLoading()
                view.empty()
            }else{
                view.showMatchNext(data.listEvents)
                view.hideLoading()
            }
        }
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }

}