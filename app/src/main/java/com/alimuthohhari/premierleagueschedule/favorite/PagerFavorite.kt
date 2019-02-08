package com.alimuthohhari.premierleagueschedule.favorite

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.alimuthohhari.premierleagueschedule.favorite.match_favorite.MatchFavorite
import com.alimuthohhari.premierleagueschedule.favorite.team_favorite.TeamFavorite

class PagerFavorite(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when (position) {
            0 -> fragment = TeamFavorite()
            1 -> fragment = MatchFavorite()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title = String()
        when (position) {
            0 -> title = "MY TEAM"
            1 -> title = "MATCH"
        }
        return title
    }

}