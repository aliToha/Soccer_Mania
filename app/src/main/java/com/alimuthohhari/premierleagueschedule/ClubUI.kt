package com.alimuthohhari.premierleagueschedule

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ClubUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        cardView {
            lparams(matchParent, wrapContent) {
                margin = dip(5)
            }
            background.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
            radius = dip(8).toFloat()

            linearLayout {
                orientation = LinearLayout.VERTICAL
                lparams(matchParent, wrapContent) {
                    padding = dip(8)
                    gravity = Gravity.CENTER
                }

                imageView {
                    id = R.id.img_club

                }.lparams(dip(50), dip(50)) {
                    gravity = Gravity.CENTER
                    topMargin = resources.getDimension(R.dimen.activity_horizontal_margin).toInt()
                }

                textView {
                    id = R.id.name_club
                    typeface = Typeface.DEFAULT_BOLD
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }.lparams(matchParent, wrapContent) {
                    margin = dip(10)
                    gravity = Gravity.CENTER
                }
            }
        }

    }
}