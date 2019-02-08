package com.alimuthohhari.premierleagueschedule.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.alimuthohhari.premierleagueschedule.DateUtils
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.db.database
import com.alimuthohhari.premierleagueschedule.helper.TestContextProvider
import com.alimuthohhari.premierleagueschedule.invisible
import com.alimuthohhari.premierleagueschedule.model.ListEvents
import com.alimuthohhari.premierleagueschedule.model.Schedule
import com.alimuthohhari.premierleagueschedule.model.Team
import com.alimuthohhari.premierleagueschedule.visible
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_event.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class DetailEvent : AppCompatActivity(), DetailView {

    private lateinit var presenter: EventDetailPresenter
    private lateinit var listEvent: ListEvents
    private lateinit var id: String

    private var menuItem: Menu? = null
    private var isSchedule: Boolean = false

    companion object {
        var EXTRADATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)

        val list = intent.getBundleExtra(EXTRADATA)

        setSupportActionBar(toolbar_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)


        val request = ApiRepo()
        val gson = Gson()
        id = list.getString("id")!!
        schadulestate()
        presenter = EventDetailPresenter(this, request, gson, TestContextProvider())
        presenter.getBadgeHome(list.getString("idHome"))
        presenter.getBadgeAway(list.getString("idAway"))
        presenter.getDetail(id)

    }

    override fun showHomeBadge(data: List<Team.TeamList>?) {
        Glide.with(this).load(data!![0].strTeamBadge)
            .apply(RequestOptions().override(250, 250))
            .into(img_home)
    }

    override fun showAwayBadge(data: List<Team.TeamList>?) {
        Glide.with(this).load(data!![0].strTeamBadge)
            .apply(RequestOptions().override(250, 250))
            .into(img_away)
    }

    override fun showDetail(data: List<ListEvents>) {
        listEvent = ListEvents(
            data[0].idEvent,
            data[0].strEvent,
            data[0].strHomeTeam,
            data[0].strAwayTeam,
            data[0].idHomeTeam,
            data[0].idAwayTeam,
            data[0].dateEvents,
            data[0].strDate,
            data[0].strTime,
            data[0].intHomeScore,
            data[0].intAwayScore,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        date_event_detail.text = DateUtils.toSimpleString(data[0].dateEvents!!)
        time_event_detail.text = data[0].strTime
        home_club.text = data[0].strHomeTeam
        away_club.text = data[0].strAwayTeam
        score_home.text = data[0].intHomeScore
        score_away.text = data[0].intAwayScore
        home_goal.text = data[0].strHomeGoalDetails
        away_goal.text = data[0].strAwayGoalDetails
        home_shoot.text = data[0].intHomeShoot
        away_shoot.text = data[0].intAwayShoot
        home_yellow.text = data[0].strHomeYellowCards
        away_yellow.text = data[0].strAwayYellowCards
        home_red.text = data[0].strHomeRedCards
        away_red.text = data[0].strAwayRedCards
        home_keeper.text = data[0].strHomeLineupGoalkeeper
        away_keeper.text = data[0].strAwayLineupGoalkeeper
        home_defense.text = data[0].strHomeLineupDefense
        away_defense.text = data[0].strAwayLineupDefense
        home_midfield.text = data[0].strHomeLineupMidfield
        away_midfield.text = data[0].strAwayLineupMidfield
        home_forward.text = data[0].strHomeLineupForward
        away_forward.text = data[0].strAwayLineupForward
        home_substitutes.text = data[0].strHomeLineupSubstitutes
        away_substitutes.text = data[0].strAwayLineupSubstitutes

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        setSchedule()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_schedule -> {
                if (isSchedule) removeFromSchedule() else addToSchedule()

                isSchedule = !isSchedule
                setSchedule()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setSchedule() {
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

    private fun removeFromSchedule() {
        try {
            database.use {
                delete(
                    Schedule.TABLE_SCHEDULE, "(ID_SCHEDULE = {id})",
                    "id" to id
                )
            }
            snackbar(container_forward, resources.getString(R.string.remove)).show()
        } catch (e: SQLiteConstraintException) {
            snackbar(container_forward, e.localizedMessage).show()
        }
    }

    private fun addToSchedule() {
        try {
            database.use {
                insert(
                    Schedule.TABLE_SCHEDULE,
                    Schedule.ID_SCHEDULE to listEvent.idEvent,
                    Schedule.DATE_SCHEDULE to listEvent.dateEvents.toString(),
                    Schedule.TIME_SCHEDULE to listEvent.strTime.toString(),
                    Schedule.ID_TEAM_HOME to listEvent.idHomeTeam,
                    Schedule.TEAM_HOME to listEvent.strHomeTeam,
                    Schedule.SCORE_TEAM_HOME to listEvent.intHomeScore,
                    Schedule.ID_TEAM_AWAY to listEvent.idAwayTeam,
                    Schedule.TEAM_AWAY to listEvent.strAwayTeam,
                    Schedule.SCORE_TEAM_AWAY to listEvent.intAwayScore
                )
            }
            container_forward.snackbar(resources.getString(R.string.add)).show()
        } catch (e: SQLiteConstraintException) {
            container_forward.snackbar(e.localizedMessage).show()
        }
    }

    override fun showLoading() {
        progress_detail.visible()
    }

    override fun hideLoading() {
        progress_detail.invisible()
    }

    private fun schadulestate() {
        database.use {
            val result = select(Schedule.TABLE_SCHEDULE)
                .whereArgs(
                    "(ID_SCHEDULE = {id})",
                    "id" to id
                )
            val schedul = result.parseList(classParser<Schedule>())
            if (!schedul.isEmpty()) isSchedule = true
        }
    }
}
