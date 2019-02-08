package com.alimuthohhari.premierleagueschedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(@SerializedName("teams") val listTeam: List<TeamList>) : Parcelable {
    @Parcelize
    data class TeamList(
        @SerializedName("idLeague")
        var idLeague: String?,
        @SerializedName("idSoccerXML")
        var idSoccerXML: String?,
        @SerializedName("idTeam")
        var idTeam: String?,
        @SerializedName("intFormedYear")
        var intFormedYear: Int?,
        var strDescriptionDE: String?,
        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String?,
        @SerializedName("strStadium")
        var strStadium: String?,
        var strStadiumThumb: String?,
        @SerializedName("strTeam")
        var strTeam: String?,
        @SerializedName("strTeamBadge")
        var strTeamBadge: String?,
        @SerializedName("strTeamBanner")
        var strTeamBanner: String?

    ) : Parcelable
}