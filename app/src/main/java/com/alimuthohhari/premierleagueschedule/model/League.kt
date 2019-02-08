package com.alimuthohhari.premierleagueschedule.model

import com.google.gson.annotations.SerializedName

data class League(@SerializedName("countrys") val leaguesList: List<LeagueList>) {
    data class LeagueList(
        @SerializedName("idLeague")
        var idLeague: String,
        @SerializedName("strLeague")
        var strLeague: String,
        @SerializedName("strLeagueAlternate")
        var strLeagueAlternate: String,
        @SerializedName("strSport")
        var strSport: String,
        @SerializedName("strBadge")
        var strBadge: String
    )
}
