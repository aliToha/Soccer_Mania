package com.alimuthohhari.premierleagueschedule.main

import android.app.job.JobInfo
import android.content.ComponentName
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.alimuthohhari.premierleagueschedule.ConnectivityCheck
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.search.Search
import kotlinx.android.synthetic.main.activity_main_navigation.*
import org.jetbrains.anko.startActivity


class MainNavigation : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    ViewPager.OnPageChangeListener {
    private lateinit var adapter: PagerMain
    private lateinit var menuItem: MenuItem
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation)

        setSupportActionBar(toolbar_main)

        adapter = PagerMain(supportFragmentManager)
        pager_main.offscreenPageLimit = 4
        pager_main.adapter = adapter
        pager_main.addOnPageChangeListener(this)
        bottom_nav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.standing -> {
                pager_main.currentItem = 0
                position = 0
            }
            R.id.schedule -> {
                pager_main.currentItem = 1
                position = 1
            }
            R.id.team -> {
                pager_main.currentItem = 2
                position = 2
            }
            R.id.favorite -> {
                pager_main.currentItem = 3
                position = 3
            }
        }
        return true
    }

    override fun onPageScrollStateChanged(p0: Int) {
        //do nothing
    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
        //do nothing
    }

    override fun onPageSelected(position: Int) {
        menuItem = bottom_nav.menu.getItem(position).setChecked(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        when (id) {
            R.id.search -> startActivity<Search>()
        }
        return super.onOptionsItemSelected(item)
    }
}
