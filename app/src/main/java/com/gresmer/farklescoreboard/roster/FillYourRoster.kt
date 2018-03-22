package com.gresmer.farklescoreboard.roster

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button

import com.gresmer.farklescoreboard.R

import java.util.ArrayList

/**
 * Created by kgresmer on 3/18/18.
 */

class FillYourRoster : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_your_roster)

        val rosterPlayerAdapter: RosterPlayerAdapter
        val rosterList = ArrayList<RosterPlayer>()
        val recyclerView = findViewById<RecyclerView>(R.id.recycle_view)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        rosterList.add(RosterPlayer(1, "Kevin", 3, 4, 10100))
        rosterList.add(RosterPlayer(2, "Sigrid", 1, 2, 10200))
        rosterList.add(RosterPlayer(3, "Kip", 6, 4, 10300))
        rosterList.add(RosterPlayer(4, "Taylor", 6, 2, 11000))
        rosterList.add(RosterPlayer(5, "Alex", 3, 1, 9700))
        rosterList.add(RosterPlayer(6, "Laura", 4, 7, 9800))
        rosterList.add(RosterPlayer(7, "Mark", 8, 7, 10150))
        rosterList.add(RosterPlayer(8, "Louise", 3, 9, 10350))

        rosterPlayerAdapter = RosterPlayerAdapter(this, rosterList)
        recyclerView.adapter = rosterPlayerAdapter
    }

}
