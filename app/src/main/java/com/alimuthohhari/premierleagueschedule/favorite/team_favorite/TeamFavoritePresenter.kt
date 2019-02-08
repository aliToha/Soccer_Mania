package com.alimuthohhari.premierleagueschedule.favorite.team_favorite

import android.content.Context
import com.alimuthohhari.premierleagueschedule.db.database
import com.alimuthohhari.premierleagueschedule.model.Myteam
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select


class TeamFavoritePresenter (
    private val view: ViewTeamFavorite,
    private val context: Context
){
    fun teamFavorite(){
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            var team:List<Myteam>? = null
            context.database.use {
                val result = select(Myteam.CLUB_TABLE)
                val data = result.parseList(classParser<Myteam>())
                team = data
            }
            if(team!!.isEmpty()){
                view.hideLoading()
                view.empty()
            }else{
                view.showTeamVaforite(team!!)
                view.hideLoading()
            }

        }
    }
}