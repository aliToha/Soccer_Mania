package com.alimuthohhari.premierleagueschedule.favorite.team_favorite


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
import com.alimuthohhari.premierleagueschedule.club.DetailClub
import com.alimuthohhari.premierleagueschedule.invisible
import com.alimuthohhari.premierleagueschedule.model.Myteam
import com.alimuthohhari.premierleagueschedule.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

class TeamFavorite : Fragment(), ViewTeamFavorite {

    private var listMyteam: MutableList<Myteam> = mutableListOf()
    private lateinit var adapterMyTeam: MyTeamAdapter
    private lateinit var presenter: TeamFavoritePresenter
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

        }.view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeRefresh.onRefresh {
            listMyteam.clear()
            presenter.teamFavorite()
        }
        adapterMyTeam = MyTeamAdapter(
            listMyteam)
            { partItem: Myteam -> teamClick(partItem) }
        recyclerView.adapter = adapterMyTeam

        presenter = TeamFavoritePresenter(this,context!!)
        presenter.teamFavorite()
    }

    private fun teamClick(partItem: Myteam) {
        val bundle = Bundle()
        bundle.putString("id", partItem.teamId)
        bundle.putString("badge", partItem.badgeTeam)
        bundle.putString("stadium", partItem.stadiumTeam)
        bundle.putString("name", partItem.nameTeam)
        bundle.putString("description", partItem.description)
        bundle.putString("formed", partItem.formedTeam)
        bundle.putString("thumb", partItem.stadiuThumb)
        startActivity<DetailClub>(DetailClub.POSITIONEXTRA to bundle)
    }

    override fun onResume() {
        super.onResume()
        presenter.teamFavorite()
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

    override fun showTeamVaforite(data: List<Myteam>) {
        swipeRefresh.isRefreshing = false
        listMyteam.clear()
        listMyteam.addAll(data)
        adapterMyTeam.notifyDataSetChanged()
    }

}
