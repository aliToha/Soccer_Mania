package com.alimuthohhari.premierleagueschedule.player

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.model.Players
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetail : AppCompatActivity() {

    companion object {
        val EXTRADATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        setSupportActionBar(toolbar_player)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val item = intent.getParcelableExtra<Players.PlayerList>(EXTRADATA)

        Glide.with(this).load(item.mStrThumb).into(img_funart)
        description.text = item.mStrDescriptionEN
        birthday.text = item.mDateBorn
        height.text = item.mStrHeight
        weight.text = item.mStrWeight
        position.text = item.mStrPosition
        national.text = item.mStrNationality
        name_player.text = item.mStrPlayer

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
