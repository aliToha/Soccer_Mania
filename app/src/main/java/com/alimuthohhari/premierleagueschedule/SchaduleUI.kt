package com.alimuthohhari.premierleagueschedule

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class SchaduleUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        cardView {
            background.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
            radius = dip(8).toFloat()
            lparams(matchParent, wrapContent) {
                margin = dip(5)
            }
            relativeLayout {
                lparams(matchParent, wrapContent) {
                    padding = dip(8)
                }

                textView {
                    id = R.id.club_home
                    maxLines = 1
                }.lparams(200, wrapContent) {
                    alignParentTop()
                    alignParentLeft()
                    marginStart = dip(16)
                }
                textView {
                    id = R.id.home_score
                    typeface = Typeface.DEFAULT_BOLD
                    textSize = dip(9).toFloat()
                }.lparams(wrapContent, wrapContent) {
                    below(R.id.club_home)
                    alignParentStart()
                    marginStart = dip(16)
                }
                textView {
                    id = R.id.vs
                    text = resources.getString(R.string.vs)
                    typeface = Typeface.DEFAULT_BOLD
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                }.lparams(wrapContent, wrapContent) {
                    centerInParent()
                    alignParentTop()
                }
                textView {
                    id = R.id.away_score
                    typeface = Typeface.DEFAULT_BOLD
                    textSize = dip(9).toFloat()
                }.lparams(wrapContent, wrapContent) {
                    below(R.id.club_away)
                    alignParentEnd()
                    marginEnd = dip(16)

                }
                textView {
                    id = R.id.club_away
                    maxLines = 1

                }.lparams(200, wrapContent) {
                    alignParentTop()
                    alignParentEnd()
                    marginEnd = dip(16)
                }

                textView {
                    id = R.id.date_event
                    typeface = Typeface.DEFAULT_BOLD
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                }.lparams(wrapContent, wrapContent) {
                    centerInParent()
                    below(R.id.vs)
                }
                textView {
                    id = R.id.time_event
                    typeface = Typeface.DEFAULT_BOLD
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                }.lparams(wrapContent, wrapContent) {
                    centerInParent()
                    below(R.id.date_event)
                }
            }
        }
    }
}