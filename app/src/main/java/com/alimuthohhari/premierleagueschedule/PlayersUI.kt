package com.alimuthohhari.premierleagueschedule

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class PlayersUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            orientation = LinearLayout.HORIZONTAL
            lparams(matchParent, wrapContent) {
                padding = dip(8)
            }
            imageView {
                id = R.id.thumb_player

            }.lparams(wrapContent, wrapContent) {
                gravity = Gravity.START
                topMargin = dip(16)
            }
            linearLayout {
                orientation = LinearLayout.VERTICAL
                lparams(wrapContent, wrapContent) {
                }
                textView {
                    id = R.id.name_player
                    typeface = Typeface.DEFAULT
                }.lparams(wrapContent, wrapContent) {
                    topMargin = dip(8)
                    marginStart = dip(16)
                }
                textView {
                    id = R.id.name_club
                    typeface = Typeface.DEFAULT
                }.lparams(wrapContent, wrapContent) {
                    bottomMargin = dip(8)
                    marginStart = dip(16)
                }
            }
            linearLayout {
                lparams(matchParent, 2) {
                    marginStart = dip(16)
                    marginEnd = dip(16)
                }
                backgroundColor = Color.GRAY
            }
        }

    }
}