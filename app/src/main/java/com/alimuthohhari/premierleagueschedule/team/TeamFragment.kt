package com.alimuthohhari.premierleagueschedule.team


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.club.DetailClub
import com.alimuthohhari.premierleagueschedule.helper.TestContextProvider
import com.alimuthohhari.premierleagueschedule.invisible
import com.alimuthohhari.premierleagueschedule.main.SpinnerAdapter
import com.alimuthohhari.premierleagueschedule.model.League
import com.alimuthohhari.premierleagueschedule.model.Team
import com.alimuthohhari.premierleagueschedule.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

class TeamFragment : Fragment(), TeamView {

    private var team: MutableList<Team.TeamList> = mutableListOf()
    private var listLeague: MutableList<League.LeagueList> = mutableListOf()
    private lateinit var adapterSpinner: SpinnerAdapter
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter
    private lateinit var spinner: Spinner
    private lateinit var image: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var leagueName = String()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                    spinner = spinner{
                        id = R.id.spinner_league
                    }.lparams(wrapContent, wrapContent) {
                        centerHorizontally()
                        alignParentTop()
                    }
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

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }
        recyclerView.layoutManager = GridLayoutManager(context, 3)

        adapter = TeamAdapter(
            team)
            { partItem: Team.TeamList -> partItemClick(partItem) }
        recyclerView.adapter = adapter
        val request = ApiRepo()
        val gson = Gson()

        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnected == true

        presenter = TeamPresenter(this@TeamFragment, request, gson, TestContextProvider())

        if(isConnected){
            presenter.getLeague()
        }else{
            toast("you dont have connection internet").show()
        }

        adapterSpinner = SpinnerAdapter(requireContext(), listLeague)
        spinner.adapter = adapterSpinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = adapterSpinner.getItem(position).idLeague
                presenter.getTeamList(leagueName)
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    private fun partItemClick(partItem: Team.TeamList) {
        val bundle = Bundle()
        bundle.putString("id", partItem.idTeam)
        bundle.putString("badge", partItem.strTeamBadge)
        bundle.putString("stadium", partItem.strStadium)
        bundle.putString("name", partItem.strTeam)
        bundle.putString("description", partItem.strDescriptionEN)
        bundle.putString("formed", partItem.intFormedYear.toString())
        bundle.putString("thumb", partItem.strStadiumThumb)
        startActivity<DetailClub>(DetailClub.POSITIONEXTRA to bundle)
    }

    override fun showLoading() {
        progressBar.visible()
        recyclerView.invisible()
        image.invisible()
    }

    override fun hideLoading() {
        presenter.getLeague()
        progressBar.invisible()
        recyclerView.visible()
        image.invisible()
    }

    override fun showLeague(data: List<League.LeagueList>) {
        listLeague.clear()
        listLeague.addAll(data)
        adapterSpinner.notifyDataSetChanged()
    }

    override fun showTeamList(data: List<Team.TeamList>) {
        swipeRefresh.isRefreshing = false
        team.clear()
        team.addAll(data)
        adapter.notifyDataSetChanged()
    }
    override fun empty() {
        swipeRefresh.isRefreshing = false
        recyclerView.invisible()
        image.visible()
    }
}
