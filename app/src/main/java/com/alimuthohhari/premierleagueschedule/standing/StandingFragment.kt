package com.alimuthohhari.premierleagueschedule.standing


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.helper.TestContextProvider
import com.alimuthohhari.premierleagueschedule.invisible
import com.alimuthohhari.premierleagueschedule.main.SpinnerAdapter
import com.alimuthohhari.premierleagueschedule.model.League
import com.alimuthohhari.premierleagueschedule.model.Season
import com.alimuthohhari.premierleagueschedule.model.Standing
import com.alimuthohhari.premierleagueschedule.visible
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast

class StandingFragment : Fragment(), StandingView {
    private var standing: MutableList<Standing.StandingList> = mutableListOf()
    private var league: MutableList<League.LeagueList> = mutableListOf()
    private var season: MutableList<Season.SeasonList> = mutableListOf()
    private lateinit var presenter: StandingPresenter
    private lateinit var adapter: StandingAdapter
    private lateinit var adapterSpinner: SpinnerAdapter
    private lateinit var progressNext: ProgressBar
    private lateinit var listStanding: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var imageLeague: ImageView
    private lateinit var image: ImageView
    private lateinit var header: LinearLayout
    private var leagueId = String()
    private var seasonId = String()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return UI {
            frameLayout {
                lparams(width = matchParent, height = wrapContent)
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                    )

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        lparams(width = matchParent, height = wrapContent)

                        spinner = spinner {
                            id = R.id.spinner_league
                        }.lparams(wrapContent, wrapContent) {
                            bottomMargin = dip(16)
                            gravity = Gravity.CENTER_HORIZONTAL
                        }

                        imageLeague = imageView {
                            id = R.id.img_club
                        }.lparams(wrapContent, wrapContent) {
                            gravity = Gravity.CENTER
                        }
                        header = linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            lparams(wrapContent, wrapContent) {
                                padding = dip(8)
                                gravity = Gravity.END
                            }
                            textView {
                                text = resources.getText(R.string.MP)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams(wrapContent, wrapContent) {
                                marginStart = dip(2)
                            }
                            textView {
                                text = resources.getText(R.string.W)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams(70, wrapContent) {
                                marginStart = dip(2)
                            }
                            textView {
                                text = resources.getText(R.string.D)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams(70, wrapContent) {
                                marginStart = dip(2)
                            }
                            textView {
                                text = resources.getText(R.string.L)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams(70, wrapContent) {
                                marginStart = dip(2)
                            }
                            textView {
                                text = resources.getText(R.string.GF)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams(70, wrapContent) {
                                marginStart = dip(2)
                            }
                            textView {
                                text = resources.getText(R.string.GA)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams(70, wrapContent) {
                                marginStart = dip(2)
                            }
                            textView {
                                text = resources.getText(R.string.GD)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams(70, wrapContent) {
                                marginStart = dip(2)
                            }
                            textView {
                                text = resources.getText(R.string.PTS)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams(100, wrapContent) {
                                marginStart = dip(5)
                            }
                        }
                        relativeLayout {

                            lparams(matchParent, wrapContent) {
                            }

                            progressNext = progressBar {

                            }.lparams(wrapContent, wrapContent) {
                                centerHorizontally()
                            }
                            listStanding = recyclerView {
                                id = R.id.standing_list
                                layoutManager = LinearLayoutManager(ctx)
                            }.lparams(matchParent, wrapContent) {
                                gravity = Gravity.END
                            }
                            image = imageView(R.drawable.ic_launcher_foreground){

                            }.lparams(matchParent, matchParent){
                                margin = resources.getDimension(R.dimen.activity_vertical_margin).toInt()
                            }
                        }
                    }
                }
            }

            swipeRefresh.onRefresh {
                presenter.getStandingList(leagueId, seasonId)
            }

            adapterSpinner = SpinnerAdapter(ctx, league)
            spinner.adapter = adapterSpinner

            adapter = StandingAdapter(standing)
            listStanding.adapter = adapter
            val request = ApiRepo()
            val gson = Gson()

            presenter = StandingPresenter(
                this@StandingFragment,
                request,
                gson,
                TestContextProvider()
            )
           // presenter.getLeagueList()

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //do nothing
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    leagueId = adapterSpinner.getItem(position).idLeague
                    Glide.with(ctx).load(adapterSpinner.getItem(position).strBadge)
                        .apply(RequestOptions().override(250, 250)).into(imageLeague)
                    presenter.getSeasonList(leagueId)
                }

            }
        }.view
    }

    override fun showLoading() {
        listStanding.invisible()
        progressNext.visible()
        header.invisible()
        image.invisible()
    }

    override fun hideLoading() {
        listStanding.visible()
        progressNext.invisible()
        header.visible()
        image.invisible()
    }

    override fun showSeasonList(data: List<Season.SeasonList>) {
        season.clear()
        season.addAll(data)
        val s = data.lastIndex
        seasonId = data[s].strSeason
        presenter.getStandingList(leagueId, seasonId)
    }

    override fun showStandingList(data: List<Standing.StandingList>) {
        swipeRefresh.isRefreshing = false
        standing.clear()
        standing.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLeagueList(data: List<League.LeagueList>) {
        league.clear()
        league.addAll(data)
        adapterSpinner.notifyDataSetChanged()

    }

    override fun empty(){
        listStanding.invisible()
        header.visible()
        image.visible()
        toast(resources.getString(R.string.empty)).show()
    }
}
