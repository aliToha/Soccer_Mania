package com.alimuthohhari.premierleagueschedule.schedule.last_event

import com.alimuthohhari.premierleagueschedule.model.League
import com.alimuthohhari.premierleagueschedule.model.ListEvents

interface LastView {
    fun showLoading()
    fun hideLoading()
    fun empty()
    fun showMatchLast(data: List<ListEvents>)
}