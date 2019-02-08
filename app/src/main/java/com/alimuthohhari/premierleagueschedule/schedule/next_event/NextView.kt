package com.alimuthohhari.premierleagueschedule.schedule.next_event

import com.alimuthohhari.premierleagueschedule.model.League
import com.alimuthohhari.premierleagueschedule.model.ListEvents

interface NextView {
    fun showLoading()
    fun hideLoading()
    fun empty()
    fun showMatchNext(data: List<ListEvents>)
}