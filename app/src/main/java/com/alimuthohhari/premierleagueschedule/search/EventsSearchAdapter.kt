package com.alimuthohhari.premierleagueschedule.search

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alimuthohhari.premierleagueschedule.DateUtils
import com.alimuthohhari.premierleagueschedule.LastEventUI
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.model.ListEvents
import org.jetbrains.anko.AnkoContext

class EventsSearchAdapter(private val items: List<ListEvents>, private val clickListener: (ListEvents) -> Unit) :
    RecyclerView.Adapter<EventsSearchAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateEvent = view.findViewById<TextView>(R.id.date_event)
        private val timeEvent = view.findViewById<TextView>(R.id.time_event)
        private val clubHome = view.findViewById<TextView>(R.id.club_home)
        private val clubAway = view.findViewById<TextView>(R.id.club_away)
        private val scoreHome = view.findViewById<TextView>(R.id.home_score)
        private val scoreAway = view.findViewById<TextView>(R.id.away_score)

        fun bindItem(items: ListEvents, clickListener: (ListEvents) -> Unit) {

            dateEvent.text = DateUtils.toSimpleString(items.dateEvents!!)
            clubHome.text = items.strHomeTeam
            clubAway.text = items.strAwayTeam
            scoreHome.text = items.intHomeScore
            scoreAway.text = items.intAwayScore
            itemView.setOnClickListener { clickListener(items) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LastEventUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, postion: Int) {
        holder.bindItem(items[postion], clickListener)
    }
}
