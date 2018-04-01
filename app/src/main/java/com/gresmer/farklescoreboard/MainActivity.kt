package com.gresmer.farklescoreboard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.gresmer.farklescoreboard.ExistingPlayers.ExistingPlayersList
import com.gresmer.farklescoreboard.roster.FillYourRoster
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onPlayerStatsClicked(view:  View) {
        val intent = Intent(this, ExistingPlayersList::class.java)
        startActivity(intent)
    }

    fun onRulesClicked(view:  View) {
        Toast.makeText(this, "Rules", Toast.LENGTH_SHORT).show()
    }

    fun onScoreAGameClicked(view:  View) {
        val intent = Intent(this, FillYourRoster::class.java)
        startActivity(intent)
    }

}


