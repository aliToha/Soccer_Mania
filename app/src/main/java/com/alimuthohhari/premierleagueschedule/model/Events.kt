package com.alimuthohhari.premierleagueschedule.model

import com.google.gson.annotations.SerializedName


data class Events(
    @SerializedName("events")
    val listEvents: List<ListEvents>
)