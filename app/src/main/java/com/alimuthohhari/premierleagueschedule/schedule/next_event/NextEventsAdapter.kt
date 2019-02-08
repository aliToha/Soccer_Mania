package com.alimuthohhari.premierleagueschedule.schedule.next_event

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.alimuthohhari.premierleagueschedule.*
import com.alimuthohhari.premierleagueschedule.model.ListEvents
import org.jetbrains.anko.AnkoContext

class NextEventsAdapter(private val items: List<ListEvents>, private val clickListener: (ListEvents) -> Unit) :
    RecyclerView.Adapter<NextEventsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private val dateEvent = view.findViewById<TextView>(R.id.date_event)
        private val timeEvent = view.findViewById<TextView>(R.id.time_event)
        private val clubHome = view.findViewById<TextView>(R.id.club_home)
        private val clubAway = view.findViewById<TextView>(R.id.club_away)
        private val scoreHome = view.findViewById<TextView>(R.id.home_score)
        private val scoreAway = view.findViewById<TextView>(R.id.away_score)
        private val imgAlert = view.findViewById<ImageView>(R.id.alert)

        fun bindItem(items: ListEvents, clickListener: (ListEvents) -> Unit) {
            dateEvent.text = DateUtils.toSimpleString(items.dateEvents!!)
            val time = timeCalnder.toSimpleString(items.strTime!!)
            timeEvent.text = calenderTime.toSimpleString(time)
            clubHome.text = items.strHomeTeam
            clubAway.text = items.strAwayTeam
            scoreHome.text = items.intHomeScore
            scoreAway.text = items.intAwayScore
            itemView.setOnClickListener { clickListener(items) }
            imgAlert.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            when (p0) {
                imgAlert ->
                    Toast.makeText(itemView.context, "ini posisi " + adapterPosition, Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NextEventUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, postion: Int) {
        holder.bindItem(items[postion], clickListener)
    }

}
