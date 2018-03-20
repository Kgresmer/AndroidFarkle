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

        rosterList.add(RosterPlayer(1, "Kevin", "3"))
        rosterList.add(RosterPlayer(2, "Sigrid", "1"))
        rosterList.add(RosterPlayer(3, "Kip", "6"))
        rosterList.add(RosterPlayer(4, "Taylor", "6"))
        rosterList.add(RosterPlayer(5, "Alex", "6"))
        rosterList.add(RosterPlayer(6, "Laura", "6"))
        rosterList.add(RosterPlayer(7, "Mark", "6"))
        rosterList.add(RosterPlayer(8, "Louise", "6"))

        rosterPlayerAdapter = RosterPlayerAdapter(this, rosterList)
        recyclerView.adapter = rosterPlayerAdapter
    }

}
