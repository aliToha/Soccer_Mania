package com.alimuthohhari.premierleagueschedule.search


import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.alimuthohhari.premierleagueschedule.R


class PagerSearch : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var id = 0
        when (position) {
            0 -> id = R.id.page_1
            1 -> id = R.id.page_2
            2 -> id = R.id.page_3
        }
        return container.findViewById(id)
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title = String()
        when (position) {
            0 -> title = "PLAYERS"
            1 -> title = "TEAMS"
            2 -> title = "MATCH"
        }
        return title
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}