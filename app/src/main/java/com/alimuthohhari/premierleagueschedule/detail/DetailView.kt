package com.alimuthohhari.premierleagueschedule.detail

import com.alimuthohhari.premierleagueschedule.model.ListEvents
import com.alimuthohhari.premierleagueschedule.model.Team

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetail(data: List<ListEvents>)
    fun showHomeBadge(data: List<Team.TeamList>?)
    fun showAwayBadge(data: List<Team.TeamList>?)
}