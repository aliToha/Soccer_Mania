package com.alimuthohhari.premierleagueschedule

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class NextEventUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        cardView {
            lparams(matchParent, wrapContent) {
                margin = dip(5)
            }
            linearLayout {
                orientation = LinearLayout.VERTICAL
                lparams(matchParent, wrapContent) {
                    padding = dip(8)
                    gravity = Gravity.CENTER
                }
                imageView(R.drawable.ic_add_alert_black_24dp) {
                    id = R.id.alert
                }.lparams(wrapContent, wrapContent) {
                    gravity = Gravity.END
                    marginEnd = dip(16)
                    topMargin = dip(16)
                }
                textView {
                    id = R.id.date_event
                    typeface = Typeface.DEFAULT_BOLD
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                }.lparams(matchParent, wrapContent) {
                    gravity = Gravity.CENTER
                }
                textView {
                    id = R.id.time_event
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                }.lparams(matchParent, wrapContent) {
                    gravity = Gravity.CENTER
                }
                relativeLayout {
                    lparams(matchParent, wrapContent) {
                        padding = dip(8)
                    }
                    textView {
                        id = R.id.club_home
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        maxLines = 1
                    }.lparams(matchParent, wrapContent) {
                        startOf(R.id.home_score)
                        marginStart = dip(16)
                        marginEnd = dip(16)
                    }
                    textView {
                        id = R.id.home_score
                        typeface = Typeface.DEFAULT_BOLD
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textSize = dip(12).toFloat()
                    }.lparams(wrapContent, wrapContent) {
                        startOf(R.id.vs)
                        marginStart = dip(16)
                    }
                    textView {
                        id = R.id.vs
                        text = resources.getString(R.string.vs)
                        typeface = Typeface.DEFAULT_BOLD
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    }.lparams(wrapContent, wrapContent) {
                        topMargin = dip(10)
                        marginStart = dip(10)
                        marginEnd = dip(10)
                        centerInParent()
                    }
                    textView {
                        id = R.id.away_score
                        typeface = Typeface.DEFAULT_BOLD
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textSize = dip(12).toFloat()
                    }.lparams(wrapContent, wrapContent) {
                        endOf(R.id.vs)
                        marginEnd = dip(16)
                    }
                    textView {
                        id = R.id.club_away
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        maxLines = 1
                    }.lparams(matchParent, wrapContent) {
                        endOf(R.id.away_score)
                        marginStart = dip(16)
                        marginEnd = dip(16)

                    }
                }
            }
        }
    }
}