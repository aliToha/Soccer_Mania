package com.alimuthohhari.premierleagueschedule.favorite.team_favorite

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alimuthohhari.premierleagueschedule.MyTeamUI
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.model.Myteam
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.AnkoContext

class MyTeamAdapter(var teamList: MutableList<Myteam>, val clickListener: (Myteam) -> Unit) :
    RecyclerView.Adapter<MyTeamAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgClub = view.findViewById<ImageView>(R.id.img_club)
        private val nameClub = view.findViewById<TextView>(R.id.name_club)
        //private val nameStadion = view.findViewById<TextView>(R.id.stadion)

        fun bindItem(items: Myteam, clickListener: (Myteam) -> Unit) {

            Glide.with(itemView.context).load(items.badgeTeam).apply(RequestOptions().override(200, 200)).into(imgClub)
            nameClub.text = items.nameTeam
            //  nameStadion.text = items.stadiumTeam
            itemView.setOnClickListener { clickListener(items) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MyTeamUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teamList[position], clickListener)
    }

}