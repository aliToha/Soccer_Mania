package com.alimuthohhari.premierleagueschedule.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.alimuthohhari.premierleagueschedule.model.Myteam
import com.alimuthohhari.premierleagueschedule.model.Schedule
import org.jetbrains.anko.db.*

class DbHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MySchedule.db", null, 1) {
    companion object {
        private var instances: DbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DbHelper {
            if (instances == null) {
                instances = DbHelper(ctx.applicationContext)
            }
            return instances as DbHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        //create table
        db.createTable(
            Myteam.CLUB_TABLE, true,
            Myteam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Myteam.ID_TEAM to TEXT,
            Myteam.NAME_TEAM to TEXT,
            Myteam.BADGE_TEAM to TEXT,
            Myteam.STADIUM_TEAM to TEXT,
            Myteam.FORMED_YEAR to TEXT,
            Myteam.STADIUM_THUMB to TEXT,
            Myteam.DESCRIPTION to TEXT
        )

        db.createTable(
            Schedule.TABLE_SCHEDULE, true,
            Schedule.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Schedule.ID_SCHEDULE to TEXT,
            Schedule.DATE_SCHEDULE to TEXT,
            Schedule.TIME_SCHEDULE to TEXT,
            Schedule.ID_TEAM_HOME to TEXT,
            Schedule.TEAM_HOME to TEXT,
            Schedule.SCORE_TEAM_HOME to TEXT,
            Schedule.ID_TEAM_AWAY to TEXT,
            Schedule.TEAM_AWAY to TEXT,
            Schedule.SCORE_TEAM_AWAY to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Schedule.TABLE_SCHEDULE, true)
        db.dropTable(Myteam.CLUB_TABLE, true)
    }

}

val Context.database: DbHelper
    get() = DbHelper.getInstance(applicationContext)