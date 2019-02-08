package com.alimuthohhari.premierleagueschedule.favorite.team_favorite

import com.alimuthohhari.premierleagueschedule.model.Myteam

interface ViewTeamFavorite {
    fun showLoading()
    fun hideLoading()
    fun empty()
    fun showTeamVaforite(data: List<Myteam>)
}