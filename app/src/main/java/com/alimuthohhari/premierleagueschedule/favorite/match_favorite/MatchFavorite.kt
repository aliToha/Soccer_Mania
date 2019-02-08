package com.alimuthohhari.premierleagueschedule.favorite.match_favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.detail.DetailEvent
import com.alimuthohhari.premierleagueschedule.invisible
import com.alimuthohhari.premierleagueschedule.model.Schedule
import com.alimuthohhari.premierleagueschedule.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

class MatchFavorite : Fragment(),ViewMatchFavorite {

    private var listMatch: MutableList<Schedule> = mutableListOf()
    private lateinit var adapterMatch: MyScheduleAdapter
    private lateinit var presenter: MatchFavoritePresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return UI {
            linearLayout {
                lparams(matchParent, wrapContent) {
                    bottomMargin = dip(5)
                }
                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(
                        R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                    )
                    relativeLayout {
                        lparams(wrapContent, wrapContent)

                        progressBar = progressBar {
                        }.lparams(wrapContent, wrapContent) {
                            centerInParent()
                        }
                        recyclerView = recyclerView {
                            layoutManager = LinearLayoutManager(ctx)
                        }.lparams(matchParent, wrapContent){

                        }
                        image = imageView(R.drawable.ic_launcher_foreground){

                        }.lparams(matchParent, matchParent){
                            margin = resources.getDimension(R.dimen.activity_vertical_margin).toInt()
                        }
                    }
                }
            }

        }.view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeRefresh.onRefresh {
            listMatch.clear()
            presenter.matchFavorite()
        }
        adapterMatch = MyScheduleAdapter(
            listMatch)
            { partItem: Schedule -> scheduleClick(partItem) }
        recyclerView.adapter = adapterMatch

        presenter = MatchFavoritePresenter(this,context!!)
        presenter.matchFavorite()
    }


    private fun scheduleClick(partItem: Schedule) {
        val bundle = Bundle()
        bundle.putString("id", partItem.scheduleId)
        bundle.putString("idHome", partItem.teamHome)
        bundle.putString("idAway", partItem.teamAway)
        startActivity<DetailEvent>(DetailEvent.EXTRADATA to bundle)
    }

    override fun onResume() {
        super.onResume()
        listMatch.clear()
        presenter.matchFavorite()
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

    override fun empty() {
        swipeRefresh.isRefreshing = false
        recyclerView.invisible()
        image.visible()
    }

    override fun showMatchVaforite(data: List<Schedule>) {
        swipeRefresh.isRefreshing = false
        listMatch.clear()
        listMatch.addAll(data)
        adapterMatch.notifyDataSetChanged()
    }

}
