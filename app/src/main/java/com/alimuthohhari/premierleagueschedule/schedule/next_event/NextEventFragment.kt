package com.alimuthohhari.premierleagueschedule.schedule.next_event

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.alimuthohhari.premierleagueschedule.*
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.helper.TestContextProvider
import com.alimuthohhari.premierleagueschedule.model.ListEvents
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class NextEventFragment : Fragment(), NextView {
    companion object {
        fun newInstance(league: String): NextEventFragment {
            val args = Bundle()
            args.putString("id", league)
            val fragment = NextEventFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var listNext: MutableList<ListEvents> = mutableListOf()
    private lateinit var adapterNext: NextEventsAdapter
    private lateinit var presenter: NextPresenter
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
                        lparams(matchParent, matchParent)
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
            presenter.getEventNext(arguments?.getString("id"))
        }

        adapterNext = NextEventsAdapter(
            listNext)
            { partItem: ListEvents -> itemClick(partItem) }
        recyclerView.adapter = adapterNext

        presenter = NextPresenter(this, request, gson, TestContextProvider())
        //presenter.getEventNext(arguments?.getString("id"))



    }

    private fun itemClick(partItem: ListEvents) {
        val string = StringBuilder()
        val date = DateUtils.toSimpleString(partItem.dateEvents!!)
        val new = string.append(date + " " + partItem.strTime)
        val milis = TimeStringMilis.toSimpleString(new.toString())
        val intent = Intent(Intent.ACTION_EDIT)
        intent.setType("vnd.android.cursor.item/event")
        intent.putExtra(CalendarContract.Events.TITLE, partItem.strEvent)
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, milis.time)
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, milis.time)
        intent.putExtra(CalendarContract.Events.ALL_DAY, false)// periodicity
        intent.putExtra(CalendarContract.Events.DESCRIPTION, partItem.strEvent)
        startActivity(intent)
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

    override fun showMatchNext(data: List<ListEvents>) {
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