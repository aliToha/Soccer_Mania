package com.alimuthohhari.premierleagueschedule.model

data class Schedule(
    val id: Long?, val scheduleId: String?, val dateSchedule: String?, val timeSchedule: String?,
    val teamHome: String?, val idTeamHome: String?,
    val scoreTeamHome: String?, val teamAway: String?, val idTeamAway: String?,
    val scoreTeamAway: String?
) {
    companion object {
        const val TABLE_SCHEDULE: String = "TABLE_SCHEDULE"
        const val ID: String = "ID_"
        const val ID_SCHEDULE = "ID_SCHEDULE"
        const val DATE_SCHEDULE = "DATE_SCHEDULE"
        const val TIME_SCHEDULE = "TIME_SCHEDULE"
        const val ID_TEAM_HOME: String = "ID_TEAM_HOME"
        const val TEAM_HOME: String = "TEAM_HOME"
        const val SCORE_TEAM_HOME: String = "SCORE_TEAM_HOME"
        const val ID_TEAM_AWAY: String = "ID_TEAM_AWAY"
        const val TEAM_AWAY: String = "TEAM_AWAY"
        const val SCORE_TEAM_AWAY: String = "SCORE_TEAM_AWAY"
    }
}