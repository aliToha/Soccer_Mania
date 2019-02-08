package com.alimuthohhari.premierleagueschedule.model

data class Myteam(
    val id: Long?, val teamId: String?, val nameTeam: String?,
    val badgeTeam: String?, val stadiumTeam: String?,
    val formedTeam: String?, val stadiuThumb: String?, val description: String?
) {
    companion object {
        const val CLUB_TABLE: String = "CLUB_TABLE"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val NAME_TEAM: String = "NAME_TEAM"
        const val BADGE_TEAM: String = "BADGE_TEAM"
        const val STADIUM_TEAM: String = "STADIUM_TEAM"
        const val FORMED_YEAR: String = "FORMED_YEAR"
        const val STADIUM_THUMB: String = "STADIUM_THUMB"
        const val DESCRIPTION: String = "DESCRIPTION"
    }
}