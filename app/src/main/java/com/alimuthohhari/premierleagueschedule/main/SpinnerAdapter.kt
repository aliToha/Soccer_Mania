package com.alimuthohhari.premierleagueschedule.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.model.League

class SpinnerAdapter(var context: Context, var list: List<League.LeagueList>) : BaseAdapter() {

    val mInflater: LayoutInflater = LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ItemViewHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.layout_spinner, parent, false)
            viewHolder = ItemViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ItemViewHolder
        }
        viewHolder.label.text = list[position].strLeague

        return view
    }

    private class ItemViewHolder(view: View?) {
        val label: TextView = view?.findViewById(R.id.label_spinner) as TextView
    }

    override fun getItem(position: Int): League.LeagueList {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }
}