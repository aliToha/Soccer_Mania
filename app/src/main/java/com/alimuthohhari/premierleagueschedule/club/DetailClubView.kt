package com.alimuthohhari.premierleagueschedule.club

import com.alimuthohhari.premierleagueschedule.model.Players

interface DetailClubView {
    fun showLoading()
    fun hideLoading()
    fun empty()
    fun showPlayer(data: List<Players.PlayerList>)
}