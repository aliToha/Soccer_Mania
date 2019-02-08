package com.alimuthohhari.premierleagueschedule.search

import com.alimuthohhari.premierleagueschedule.model.ListEvents
import com.alimuthohhari.premierleagueschedule.model.Players
import com.alimuthohhari.premierleagueschedule.model.Team

interface FindView {
    fun showLoading()
    fun hideLoading()
    fun showMatch(data: List<ListEvents>?)
    fun showPlayer(data: List<Players.PlayerList>?)
    fun showTeam(data: List<Team.TeamList>?)
}