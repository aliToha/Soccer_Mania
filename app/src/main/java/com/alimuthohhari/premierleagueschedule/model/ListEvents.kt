package com.alimuthohhari.premierleagueschedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ListEvents(
    @SerializedName("idEvent")
    var idEvent: String?,
    @SerializedName("strEvent")
    var strEvent: String?,
    @SerializedName("strHomeTeam")
    var strHomeTeam: String?,
    @SerializedName("strAwayTeam")
    var strAwayTeam: String?,
    @SerializedName("idHomeTeam")
    var idHomeTeam: String?,
    @SerializedName("idAwayTeam")
    var idAwayTeam: String?,
    @SerializedName("dateEvent")
    var dateEvents: Date?,
    @SerializedName("strDate")
    var strDate: String?,
    @SerializedName("strTime")
    var strTime: String?,
    @SerializedName("intHomeScore")
    var intHomeScore: String?,
    @SerializedName("intAwayScore")
    var intAwayScore: String?,
    @SerializedName("intHomeShots")
    var intHomeShoot: String?,
    @SerializedName("intAwayShots")
    var intAwayShoot: String?,
    @SerializedName("strHomeGoalDetails")
    var strHomeGoalDetails: String?,
    @SerializedName("strAwayGoalDetails")
    var strAwayGoalDetails: String?,
    @SerializedName("strHomeRedCards")
    var strHomeRedCards: String?,
    @SerializedName("strAwayRedCards")
    var strAwayRedCards: String?,
    @SerializedName("strHomeYellowCards")
    var strHomeYellowCards: String?,
    @SerializedName("strAwayYellowCards")
    var strAwayYellowCards: String?,
    @SerializedName("strHomeLineupGoalkeeper")
    var strHomeLineupGoalkeeper: String?,
    @SerializedName("strAwayLineupGoalkeeper")
    var strAwayLineupGoalkeeper: String?,
    @SerializedName("strHomeLineupDefense")
    var strHomeLineupDefense: String?,
    @SerializedName("strAwayLineupDefense")
    var strAwayLineupDefense: String?,
    @SerializedName("strHomeLineupMidfield")
    var strHomeLineupMidfield: String?,
    @SerializedName("strAwayLineupMidfield")
    var strAwayLineupMidfield: String?,
    @SerializedName("strHomeLineupForward")
    var strHomeLineupForward: String?,
    @SerializedName("strAwayLineupForward")
    var strAwayLineupForward: String?,
    @SerializedName("strHomeLineupSubstitutes")
    var strHomeLineupSubstitutes: String?,
    @SerializedName("strAwayLineupSubstitutes")
    var strAwayLineupSubstitutes: String?
) : Parcelable
