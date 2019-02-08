package com.alimuthohhari.premierleagueschedule.api

import android.net.Uri
import com.alimuthohhari.premierleagueschedule.BuildConfig

object ApiInterface {
    fun getEventsLast(league: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.MY_TSDB)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", league)
            .build()
            .toString()
    }

    fun getEventsNext(league: String?): String {
        //return (BuildConfig.BASE_URL)+"/api/v1/json/1/eventnextleague.php?id="+league
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.MY_TSDB)
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id", league)
            .build()
            .toString()
    }

    fun getBadge(idTeam: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.MY_TSDB)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", idTeam)
            .build()
            .toString()
    }

    fun getDetail(idEvent: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.MY_TSDB)
            .appendPath("lookupevent.php")
            .appendQueryParameter("id", idEvent)
            .build()
            .toString()
    }

    fun getLeague(): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.MY_TSDB)
            .appendPath("search_all_leagues.php")
            .appendQueryParameter("s", "Soccer")
            .build()
            .toString()
    }

    fun getStanding(league: String, season: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.MY_TSDB)
            .appendPath("lookuptable.php")
            .appendQueryParameter("l", league)
            .appendQueryParameter("s", season)
            .build()
            .toString()
    }

    fun getSeason(league: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.MY_TSDB)
            .appendPath("search_all_seasons.php")
            .appendQueryParameter("id", league)
            .build()
            .toString()
    }

    fun getAllTeam(league: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.MY_TSDB)
            .appendPath("lookup_all_teams.php")
            .appendQueryParameter("id", league)
            .build()
            .toString()
    }

    fun getAllPlayers(club: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.MY_TSDB)
            .appendPath("lookup_all_players.php")
            .appendQueryParameter("id", club)
            .build()
            .toString()
    }

    fun getPlayers(search: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.MY_TSDB)
            .appendPath("searchplayers.php")
            .appendQueryParameter("p", search)
            .build()
            .toString()
    }

    fun getMatch(search: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.MY_TSDB)
            .appendPath("searchevents.php")
            .appendQueryParameter("e", search)
            .build()
            .toString()
    }

    fun getClub(search: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.MY_TSDB)
            .appendPath("searchteams.php")
            .appendQueryParameter("t", search)
            .build()
            .toString()
    }

}