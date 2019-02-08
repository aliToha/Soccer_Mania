package com.alimuthohhari.premierleagueschedule.standing

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.StandingUI
import com.alimuthohhari.premierleagueschedule.model.Standing
import org.jetbrains.anko.AnkoContext

class StandingAdapter(private val items: List<Standing.StandingList>) :
    RecyclerView.Adapter<StandingAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameClub = view.findViewById<TextView>(R.id.name_club)
        private val played = view.findViewById<TextView>(R.id.MP)
        private val win = view.findViewById<TextView>(R.id.W)
        private val loss = view.findViewById<TextView>(R.id.L)
        private val draw = view.findViewById<TextView>(R.id.D)
        private val goalsFor = view.findViewById<TextView>(R.id.GF)
        private val goalsAgaints = view.findViewById<TextView>(R.id.GA)
        private val goalsDifferent = view.findViewById<TextView>(R.id.GD)
        private val points = view.findViewById<TextView>(R.id.PTS)

        fun bindItem(items: Standing.StandingList) {
            goalsAgaints.text = items.mGoalsagainst.toString()
            goalsDifferent.text = items.mGoalsdifference.toString()
            win.text = items.mWin.toString()
            loss.text = items.mLoss.toString()
            points.text = items.mTotal.toString()
            nameClub.text = items.mName
            items.mTeamid
            draw.text = items.mDraw.toString()
            goalsFor.text = items.mGoalsfor.toString()
            played.text = items.mPlayed.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            StandingUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, postion: Int) {
        holder.bindItem(items[postion])
    }
}

