package com.alimuthohhari.premierleagueschedule.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alimuthohhari.premierleagueschedule.R
import kotlinx.android.synthetic.main.fragment_my_favorite.*

class MyFavoriteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pager_favorite.offscreenPageLimit = 2
        pager_favorite.adapter =
                PagerFavorite(activity?.supportFragmentManager)
        tabs_event.setupWithViewPager(pager_favorite)
    }

}
