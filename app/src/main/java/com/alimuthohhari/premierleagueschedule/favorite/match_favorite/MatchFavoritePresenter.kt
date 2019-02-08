package com.alimuthohhari.premierleagueschedule.favorite.match_favorite

import android.content.Context
import com.alimuthohhari.premierleagueschedule.db.database
import com.alimuthohhari.premierleagueschedule.model.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class MatchFavoritePresenter (
    private val view: ViewMatchFavorite,
    private val context: Context
    ){
        fun matchFavorite(){
            view.showLoading()
            GlobalScope.launch(Dispatchers.Main) {
                var team:List<Schedule>? = null
                context.database.use {
                    val result = select(Schedule.TABLE_SCHEDULE)
                    val data = result.parseList(classParser<Schedule>())
                    team = data
                }
                if(team!!.isEmpty()){
                    view.hideLoading()
                    view.empty()
                }else{
                    view.showMatchVaforite(team!!)
                    view.hideLoading()
                }

            }
        }
}