package com.alimuthohhari.premierleagueschedule.favorite.match_favorite

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alimuthohhari.premierleagueschedule.*
import com.alimuthohhari.premierleagueschedule.model.Schedule
import org.jetbrains.anko.AnkoContext

class MyScheduleAdapter(
    private var scheduleList: MutableList<Schedule>,
    private val clickListener: (Schedule) -> Unit
) :
    RecyclerView.Adapter<MyScheduleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateEvent = view.findViewById<TextView>(R.id.date_event)
        private val timeEvent = view.findViewById<TextView>(R.id.time_event)
        private val clubHome = view.findViewById<TextView>(R.id.club_home)
        private val clubAway = view.findViewById<TextView>(R.id.club_away)
        private val scoreHome = view.findViewById<TextView>(R.id.home_score)
        private val scoreAway = view.findViewById<TextView>(R.id.away_score)

        fun bindItem(items: Schedule, clickListener: (Schedule) -> Unit) {
            val newDate = StringUtils.toSimpleString(items.dateSchedule!!)
            dateEvent.text = DateUtils.toSimpleString(newDate)
            val time = timeCalnder.toSimpleString(items.timeSchedule!!)
            timeEvent.text = calenderTime.toSimpleString(time)
            clubHome.text = items.idTeamHome
            clubAway.text = items.idTeamAway
            scoreHome.text = items.scoreTeamHome
            scoreAway.text = items.scoreTeamAway
            itemView.setOnClickListener { clickListener(items) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SchaduleUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = scheduleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(scheduleList[position], clickListener)
    }

}
