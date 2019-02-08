package com.alimuthohhari.premierleagueschedule.model

import com.google.gson.annotations.SerializedName

data class Season(@SerializedName("seasons") var seasonsList: List<SeasonList>) {
    data class SeasonList(
        @SerializedName("strSeason")
        var strSeason: String
    )
}
