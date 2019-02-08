package com.alimuthohhari.premierleagueschedule.team

import com.alimuthohhari.premierleagueschedule.model.League
import com.alimuthohhari.premierleagueschedule.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun empty()
    fun showTeamList(data: List<Team.TeamList>)
    fun showLeague(data: List<League.LeagueList>)
}