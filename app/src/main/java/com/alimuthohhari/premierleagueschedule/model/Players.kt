package com.alimuthohhari.premierleagueschedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Players(@SerializedName("player") var playerList: List<PlayerList>) {
    @Parcelize
    data class PlayerList(
        @SerializedName("dateBorn")
        var mDateBorn: String?,
        @SerializedName("dateSigned")
        var mDateSigned: String?,
        @SerializedName("idPlayer")
        var mIdPlayer: String?,
        @SerializedName("idPlayerManager")
        var mIdPlayerManager: String?,
        @SerializedName("idSoccerXML")
        var mIdSoccerXML: String?,
        @SerializedName("idTeam")
        var mIdTeam: String?,
        @SerializedName("intLoved")
        var mIntLoved: String?,
        @SerializedName("intSoccerXMLTeamID")
        var mIntSoccerXMLTeamID: String?,
        @SerializedName("strBirthLocation")
        var mStrBirthLocation: String?,
        @SerializedName("strCutout")
        var mStrCutout: String?,
        @SerializedName("strDescriptionEN")
        var mStrDescriptionEN: String?,
        @SerializedName("strFacebook")
        var mStrFacebook: String?,
        @SerializedName("strFanart1")
        var mStrFanart1: String?,
        @SerializedName("strFanart2")
        var mStrFanart2: String?,
        @SerializedName("strFanart3")
        var mStrFanart3: String?,
        @SerializedName("strFanart4")
        var mStrFanart4: String?,
        @SerializedName("strGender")
        var mStrGender: String?,
        @SerializedName("strHeight")
        var mStrHeight: String?,
        @SerializedName("strInstagram")
        var mStrInstagram: String?,
        @SerializedName("strLocked")
        var mStrLocked: String?,
        @SerializedName("strNationality")
        var mStrNationality: String?,
        @SerializedName("strPlayer")
        var mStrPlayer: String?,
        @SerializedName("strPosition")
        var mStrPosition: String?,
        @SerializedName("strSigning")
        var mStrSigning: String?,
        @SerializedName("strSport")
        var mStrSport: String?,
        @SerializedName("strTeam")
        var mStrTeam: String?,
        @SerializedName("strThumb")
        var mStrThumb: String?,
        @SerializedName("strTwitter")
        var mStrTwitter: String?,
        @SerializedName("strWage")
        var mStrWage: String?,
        @SerializedName("strWebsite")
        var mStrWebsite: String?,
        @SerializedName("strWeight")
        var mStrWeight: String?,
        @SerializedName("strYoutube")
        var mStrYoutube: String?
    ) : Parcelable
}