package com.gresmer.farklescoreboard.Scoreboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gresmer.farklescoreboard.R

class Scoreboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)
        setSupportActionBar(findViewById(R.id.scoreboard_toolbar))
    }
}
