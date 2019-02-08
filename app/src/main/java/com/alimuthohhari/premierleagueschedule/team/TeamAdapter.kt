package com.alimuthohhari.premierleagueschedule.team

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alimuthohhari.premierleagueschedule.ClubUI
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.model.Team
import com.bumptech.glide.Glide
import org.jetbrains.anko.AnkoContext

class TeamAdapter(private val items: List<Team.TeamList>, private val clickListener: (Team.TeamList) -> Unit) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameClub = view.findViewById<TextView>(R.id.name_club)
        private val imgClub = view.findViewById<ImageView>(R.id.img_club)

        fun bindItem(items: Team.TeamList, clickListener: (Team.TeamList) -> Unit) {
            nameClub.text = items.strTeam
            Glide.with(itemView.context).load(items.strTeamBadge).into(imgClub)
            itemView.setOnClickListener { clickListener(items) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ClubUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, postion: Int) {
        holder.bindItem(items[postion], clickListener)
    }
}