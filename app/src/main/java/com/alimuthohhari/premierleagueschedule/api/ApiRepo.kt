package com.alimuthohhari.premierleagueschedule.api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class ApiRepo {
    fun doRequest(url: String): Deferred<String> = GlobalScope.async {
        try {
            URL(url).readText()
        }catch(tr:IndexOutOfBoundsException){
            tr.localizedMessage
        }
    }
}