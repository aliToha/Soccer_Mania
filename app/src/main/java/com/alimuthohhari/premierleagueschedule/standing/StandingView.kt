package com.alimuthohhari.premierleagueschedule.standing

import com.alimuthohhari.premierleagueschedule.model.League
import com.alimuthohhari.premierleagueschedule.model.Season
import com.alimuthohhari.premierleagueschedule.model.Standing

interface StandingView {
    fun showLoading()
    fun hideLoading()
    fun empty()
    fun showLeagueList(data: List<League.LeagueList>)
    fun showSeasonList(data: List<Season.SeasonList>)
    fun showStandingList(data: List<Standing.StandingList>)
}