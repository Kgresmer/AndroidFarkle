package com.gresmer.farklescoreboard.Scoreboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gresmer.farklescoreboard.R
import com.gresmer.farklescoreboard.RosterPlayer
import java.util.ArrayList

class Scoreboard : AppCompatActivity() {

    var rosterList = ArrayList<RosterPlayer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)
        val data = getIntent().getExtras()
        if (data != null) rosterList = data.getParcelableArrayList("ROSTER")

    }
}
