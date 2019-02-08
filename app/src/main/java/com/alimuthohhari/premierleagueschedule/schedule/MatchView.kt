package com.alimuthohhari.premierleagueschedule.schedule

import com.alimuthohhari.premierleagueschedule.model.League
import com.alimuthohhari.premierleagueschedule.model.ListEvents

interface MatchView {
    fun showLeague(data: List<League.LeagueList>)
}