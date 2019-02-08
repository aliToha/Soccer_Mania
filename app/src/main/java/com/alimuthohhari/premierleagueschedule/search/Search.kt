package com.alimuthohhari.premierleagueschedule.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.club.DetailClub
import com.alimuthohhari.premierleagueschedule.club.PlayersClubAdapter
import com.alimuthohhari.premierleagueschedule.detail.DetailEvent
import com.alimuthohhari.premierleagueschedule.helper.TestContextProvider
import com.alimuthohhari.premierleagueschedule.invisible
import com.alimuthohhari.premierleagueschedule.model.ListEvents
import com.alimuthohhari.premierleagueschedule.model.Players
import com.alimuthohhari.premierleagueschedule.model.Team
import com.alimuthohhari.premierleagueschedule.player.PlayerDetail
import com.alimuthohhari.premierleagueschedule.team.TeamAdapter
import com.alimuthohhari.premierleagueschedule.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.abc_activity_chooser_view.*
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity

class Search : AppCompatActivity(), FindView {

    private var playerList: MutableList<Players.PlayerList> = mutableListOf()
    private var matchList: MutableList<ListEvents> = mutableListOf()
    private var teamList: MutableList<Team.TeamList> = mutableListOf()
    private lateinit var presenter: SearchPresenter
    private lateinit var pager: PagerSearch
    private lateinit var adapterPlayers: PlayersClubAdapter
    private lateinit var adapterMatch: EventsSearchAdapter
    private lateinit var adapterClub: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar_search)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pager = PagerSearch()
        pager_search.adapter = pager
        tab_search.setupWithViewPager(pager_search)
        pager_search.offscreenPageLimit = 3

        val request = ApiRepo()
        val gson = Gson()

        search_player.layoutManager = LinearLayoutManager(this)
        search_match.layoutManager = LinearLayoutManager(this)
        search_club.layoutManager = LinearLayoutManager(this)

        adapterClub = TeamAdapter(
            teamList,
            { team: Team.TeamList -> teamClick(team) })
        adapterPlayers = PlayersClubAdapter(
            playerList,
            { player: Players.PlayerList -> playerClick(player) })
        adapterMatch = EventsSearchAdapter(
            matchList,
            { match: ListEvents -> matchClick(match) })

        search_club.adapter = adapterClub
        search_match.adapter = adapterMatch
        search_player.adapter = adapterPlayers

        presenter = SearchPresenter(this, request, gson, TestContextProvider())
    }

    private fun teamClick(partItem: Team.TeamList) {
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

    private fun playerClick(partItem: Players.PlayerList) {
        startActivity<PlayerDetail>(PlayerDetail.EXTRADATA to partItem)

    }

    private fun matchClick(partItem: ListEvents) {
        val bundle = Bundle()
        bundle.putString("id", partItem.idEvent)
        bundle.putString("idHome", partItem.idHomeTeam)
        bundle.putString("idAway", partItem.idAwayTeam)
        startActivity<DetailEvent>(DetailEvent.EXTRADATA to bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val search = menu.findItem(R.id.search).actionView as SearchView
        search.isSubmitButtonEnabled
        search.requestFocus()
        search.isIconfiedByDefault
        search.isIconified = false
        search.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                finish()
                return true
            }

        })
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String): Boolean {

                return false
            }

            override fun onQueryTextChange(text: String): Boolean {
                presenter.getMatchList(text)
                presenter.getPlayerList(text)
                presenter.getTeamList(text)

                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        progress_search.visible()
        search_player.invisible()
        search_match.invisible()
        search_club.invisible()

    }

    override fun hideLoading() {
        progress_search.invisible()
        search_player.visible()
        search_match.visible()
        search_club.visible()
    }

    override fun showMatch(data: List<ListEvents>?) {
        matchList.clear()
        matchList.addAll(data!!)
        adapterMatch.notifyDataSetChanged()
    }

    override fun showPlayer(data: List<Players.PlayerList>?) {
        playerList.clear()
        playerList.addAll(data!!)
        adapterPlayers.notifyDataSetChanged()
    }

    override fun showTeam(data: List<Team.TeamList>?) {
        teamList.clear()
        teamList.addAll(data!!)
        adapterClub.notifyDataSetChanged()
    }

}
