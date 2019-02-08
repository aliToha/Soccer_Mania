package com.alimuthohhari.premierleagueschedule.model

import com.google.gson.annotations.SerializedName


data class EventsSearch(
    @SerializedName("event")
    val listEvents: List<ListEvents>
) {

}