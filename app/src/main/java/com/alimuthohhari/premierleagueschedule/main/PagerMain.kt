package com.alimuthohhari.premierleagueschedule.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.alimuthohhari.premierleagueschedule.favorite.MyFavoriteFragment
import com.alimuthohhari.premierleagueschedule.schedule.MatchFragment
import com.alimuthohhari.premierleagueschedule.standing.StandingFragment
import com.alimuthohhari.premierleagueschedule.team.TeamFragment

class PagerMain(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when (position) {
            0 -> fragment = StandingFragment()
            1 -> fragment = MatchFragment()
            2 -> fragment = TeamFragment()
            3 -> fragment = MyFavoriteFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 4
    }

}