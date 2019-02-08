package com.alimuthohhari.premierleagueschedule.club

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alimuthohhari.premierleagueschedule.PlayersUI
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.model.Players
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.AnkoContext

class PlayersClubAdapter(
    private val items: List<Players.PlayerList>,
    private val clickListener: (Players.PlayerList) -> Unit
) :
    RecyclerView.Adapter<PlayersClubAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val namePlayer = view.findViewById<TextView>(R.id.name_player)
        private val nameClub = view.findViewById<TextView>(R.id.name_club)
        private val imgClub = view.findViewById<ImageView>(R.id.thumb_player)

        fun bindItem(items: Players.PlayerList, clickListener: (Players.PlayerList) -> Unit) {
            nameClub.text = items.mStrPosition
            namePlayer.text = items.mStrPlayer
            Glide.with(itemView.context).load(items.mStrCutout).apply(RequestOptions().override(250, 250)).into(imgClub)
            itemView.setOnClickListener { clickListener(items) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PlayersUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, postion: Int) {
        holder.bindItem(items[postion], clickListener)
    }
}