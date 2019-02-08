package com.alimuthohhari.premierleagueschedule.favorite.match_favorite

import com.alimuthohhari.premierleagueschedule.model.Schedule

interface ViewMatchFavorite {
    fun showLoading()
    fun hideLoading()
    fun empty()
    fun showMatchVaforite(data: List<Schedule>)
}