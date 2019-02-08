package com.alimuthohhari.premierleagueschedule.schedule.last_event

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.detail.DetailEvent
import com.alimuthohhari.premierleagueschedule.helper.TestContextProvider
import com.alimuthohhari.premierleagueschedule.invisible
import com.alimuthohhari.premierleagueschedule.main.SpinnerAdapter
import com.alimuthohhari.premierleagueschedule.model.League
import com.alimuthohhari.premierleagueschedule.model.ListEvents
import com.alimuthohhari.premierleagueschedule.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

class LastEventFragment : Fragment(), LastView {

    companion object {

        fun newInstance(league: String): LastEventFragment {
            val args = Bundle()
            args.putString("id", league)
            val fragment = LastEventFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var listNext: MutableList<ListEvents> = mutableListOf()
    private lateinit var adapterNext: LastEventsAdapter
    private lateinit var presenter: LastPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return UI {
                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(
                        R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                    )
                    relativeLayout {
                        lparams(matchParent, wrapContent)
                        progressBar = progressBar {
                        }.lparams(wrapContent, wrapContent) {
                            centerInParent()
                        }
                        recyclerView = recyclerView {
                            layoutManager = LinearLayoutManager(ctx)
                        }.lparams(matchParent, wrapContent) {
                            topMargin = dip(10)
                            below(R.id.spinner_league)
                        }
                        image = imageView(R.drawable.ic_launcher_foreground){

                        }.lparams(matchParent, matchParent){
                            margin = resources.getDimension(R.dimen.activity_vertical_margin).toInt()
                        }

                    }
                }

        }.view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepo()
        val gson = Gson()

        swipeRefresh.onRefresh {
            listNext.clear()
            presenter.getEventLast(arguments?.getString("id"))
        }

        adapterNext = LastEventsAdapter(
            listNext)
            { partItem: ListEvents -> itemClick(partItem) }
        recyclerView.adapter = adapterNext

        presenter = LastPresenter(this, request, gson, TestContextProvider())
        //presenter.getEventLast(arguments?.getString("id"))

    }

    private fun itemClick(partItem: ListEvents) {
        val bundle = Bundle()
        bundle.putString("id", partItem.idEvent)
        bundle.putString("idHome", partItem.idHomeTeam)
        bundle.putString("idAway", partItem.idAwayTeam)
        startActivity<DetailEvent>(DetailEvent.EXTRADATA to bundle)
    }

    override fun showLoading() {
        progressBar.visible()
        recyclerView.invisible()
        image.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        recyclerView.visible()
        image.invisible()
    }

    override fun showMatchLast(data: List<ListEvents>) {
        swipeRefresh.isRefreshing = false
        listNext.clear()
        listNext.addAll(data)
        adapterNext.notifyDataSetChanged()
    }

    override fun empty() {
        swipeRefresh.isRefreshing = false
        recyclerView.invisible()
        image.visible()
    }
}