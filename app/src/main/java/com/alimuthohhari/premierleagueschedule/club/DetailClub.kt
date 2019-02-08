package com.alimuthohhari.premierleagueschedule.club

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.db.database
import com.alimuthohhari.premierleagueschedule.helper.TestContextProvider
import com.alimuthohhari.premierleagueschedule.invisible
import com.alimuthohhari.premierleagueschedule.model.Myteam
import com.alimuthohhari.premierleagueschedule.model.Players
import com.alimuthohhari.premierleagueschedule.player.PlayerDetail
import com.alimuthohhari.premierleagueschedule.visible
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_club.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.startActivity

class DetailClub : AppCompatActivity(), DetailClubView {

    private var playerList: MutableList<Players.PlayerList> = mutableListOf()
    private lateinit var presenter: DetailClubPresenter
    private lateinit var adapter: PlayersClubAdapter
    private lateinit var adapterPager: PagerClub
    private lateinit var id: String
    private var badgeTeam: String? = "badge item"
    private var nameTeam: String? = "name team"
    private var stadiumTeam: String? = "stadium team"
    private var stadiumThumb: String? = "stadium thumb"
    private var desCription: String? = "description"
    private var formedYear: String? = "former year"
    private var menuItem: Menu? = null
    private var isSchedule: Boolean = false

    companion object {
        val POSITIONEXTRA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_club)

        setSupportActionBar(toolbar_team_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapterPager = PagerClub()
        pager_club.adapter = adapterPager
        tab_club.setupWithViewPager(pager_club)

        val request = ApiRepo()
        val gson = Gson()
        val teamSelected = intent.getBundleExtra(POSITIONEXTRA)

        id = teamSelected.getString("id")!!
        badgeTeam = teamSelected.getString("badge")
        stadiumTeam = teamSelected.getString("stadium")
        nameTeam = teamSelected.getString("name")
        desCription = teamSelected.getString("description")
        formedYear = teamSelected.getString("formed")
        stadiumThumb = teamSelected.getString("thumb")

        description.text = desCription
        standFor.text = formedYear
        stadion.text = stadiumTeam
        name_club.text = nameTeam
        Glide.with(this).load(stadiumThumb).into(img_funart)
        Glide.with(this).load(badgeTeam).apply(RequestOptions().override(250, 250)).into(badge)

        detail_club.layoutManager = LinearLayoutManager(this)
        adapter = PlayersClubAdapter(
            playerList,
            { partItem: Players.PlayerList -> partItemClick(partItem) })
        detail_club.adapter = adapter

        presenter = DetailClubPresenter(this, request, gson, TestContextProvider())
        presenter.getPlayers(teamSelected.getString("id")!!)

        faforiteState()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_schedule -> {
                if (isSchedule) removeFromFavorite() else addToFavorite()

                isSchedule = !isSchedule
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isSchedule)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(
                this,
                R.drawable.ic_add_to
            )
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(
                this,
                R.drawable.ic_added_to
            )
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    Myteam.CLUB_TABLE, "(ID_TEAM = {id})",
                    "id" to id
                )
            }
            overview.snackbar(resources.getString(R.string.remove_club)).show()
        } catch (e: SQLiteConstraintException) {
            overview.snackbar(e.localizedMessage).show()
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    Myteam.CLUB_TABLE,
                    Myteam.ID_TEAM to id,
                    Myteam.NAME_TEAM to nameTeam,
                    Myteam.BADGE_TEAM to badgeTeam,
                    Myteam.STADIUM_TEAM to stadiumTeam,
                    Myteam.FORMED_YEAR to formedYear.toString(),
                    Myteam.STADIUM_THUMB to stadiumThumb,
                    Myteam.DESCRIPTION to desCription
                )
            }
            overview.snackbar(resources.getString(R.string.add_club)).show()
        } catch (e: SQLiteConstraintException) {
            overview.snackbar(e.localizedMessage).show()
        }
    }

    private fun partItemClick(partItem: Players.PlayerList) {
        startActivity<PlayerDetail>(PlayerDetail.EXTRADATA to partItem)
    }

    override fun showLoading() {
        detail_club.invisible()
        progress_detail_club.visible()
        image.invisible()
    }

    override fun hideLoading() {
        detail_club.visible()
        progress_detail_club.invisible()
        image.invisible()
    }

    override fun empty() {
        image.visible()
        detail_club.invisible()
    }

    override fun showPlayer(data: List<Players.PlayerList>) {
        playerList.clear()
        playerList.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private fun faforiteState() {
        database.use {
            val result = select(Myteam.CLUB_TABLE)
                .whereArgs(
                    "(ID_TEAM = {id})",
                    "id" to id
                )
            val club = result.parseList(classParser<Myteam>())
            if (!club.isEmpty()) isSchedule = true
        }
    }

}
