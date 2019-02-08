package com.alimuthohhari.premierleagueschedule.schedule

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class PagerMatch(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    private val fragments = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title = String()
        when (position) {
            0 -> title = "NEXT EVENT"
            1 -> title = "LAST EVENT"
        }
        return title
    }
    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }
}