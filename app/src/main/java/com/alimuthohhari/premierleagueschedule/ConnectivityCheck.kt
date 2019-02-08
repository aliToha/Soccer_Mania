package com.alimuthohhari.premierleagueschedule

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class ConnectivityCheck:BroadcastReceiver() {
    companion object {
       var connectionListener: ConnectionListener? = null
    }
    override fun onReceive(context: Context?, arg: Intent?) {
        if(connectionListener !=null){
            connectionListener!!.onNetworkChange(isConnected(context))
        }
    }

    private fun isConnected(context: Context?): Boolean {
        val connManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    interface ConnectionListener {
        fun onNetworkChange(isConnected: Boolean)
    }
}