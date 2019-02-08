package com.alimuthohhari.premierleagueschedule

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class StandingUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        cardView {
            lparams(matchParent, wrapContent) {
                background.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                bottomMargin = dip(5)
                radius = dip(8).toFloat()
            }
            linearLayout {
                orientation = LinearLayout.VERTICAL
                lparams(matchParent, wrapContent) {
                    gravity = Gravity.END
                }
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    lparams(wrapContent, wrapContent) {
                        padding = dip(8)
                        gravity = Gravity.END
                    }
                    textView {
                        id = R.id.name_club
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        maxLines = 1
                    }.lparams(wrapContent, wrapContent) {
                        marginStart = dip(2)
                        marginEnd = dip(5)
                        weight = dip(1).toFloat()
                    }
                    textView {
                        id = R.id.MP
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(70, wrapContent) {
                        marginStart = dip(2)
                    }
                    textView {
                        id = R.id.W
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(70, wrapContent) {
                        marginStart = dip(2)
                    }
                    textView {
                        id = R.id.D
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(70, wrapContent) {
                        marginStart = dip(2)
                    }
                    textView {
                        id = R.id.L
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(70, wrapContent) {
                        marginStart = dip(2)

                    }
                    textView {
                        id = R.id.GF
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(70, wrapContent) {
                        marginStart = dip(2)
                    }
                    textView {
                        id = R.id.GA
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(70, wrapContent) {
                        marginStart = dip(2)
                    }
                    textView {
                        id = R.id.GD
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(70, wrapContent) {
                        marginStart = dip(2)
                    }
                    textView {
                        id = R.id.PTS
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(100, wrapContent) {
                        marginStart = dip(5)
                    }
                }
            }
        }
    }
}