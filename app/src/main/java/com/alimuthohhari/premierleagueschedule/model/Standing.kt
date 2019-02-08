package com.alimuthohhari.premierleagueschedule.model

import com.google.gson.annotations.SerializedName

data class Standing(@SerializedName("table") var standingList: List<StandingList>) {
    data class StandingList(
        @SerializedName("draw")
        var mDraw: Long,
        @SerializedName("goalsagainst")
        var mGoalsagainst: Long,
        @SerializedName("goalsdifference")
        var mGoalsdifference: Long,
        @SerializedName("goalsfor")
        var mGoalsfor: Long,
        @SerializedName("loss")
        var mLoss: Long,
        @SerializedName("name")
        var mName: String,
        @SerializedName("played")
        var mPlayed: Long,
        @SerializedName("teamid")
        var mTeamid: String,
        @SerializedName("total")
        var mTotal: Long,
        @SerializedName("win")
        var mWin: Long

    )

}