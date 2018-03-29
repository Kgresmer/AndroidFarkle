package com.gresmer.farklescoreboard.ExistingPlayers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.gresmer.farklescoreboard.R
import com.gresmer.farklescoreboard.roster.FillYourRoster
import com.gresmer.farklescoreboard.roster.RosterPlayer
import java.util.ArrayList

class ExistingPlayersList : AppCompatActivity() {

    var rosterList = ArrayList<RosterPlayer>()
    var existingPlayerList = ArrayList<RosterPlayer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_existing_players_list)
        val data = getIntent().getExtras()
        if (data != null) rosterList = data.getParcelableArrayList("ROSTER")

        renderExistingPlayerRecyclerView()

    }

    fun renderExistingPlayerRecyclerView() {
        val rosterPlayerAdapter: ExistingPlayerListAdapter

        val recyclerView = findViewById<RecyclerView>(R.id.recycle_view)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        rosterPlayerAdapter = ExistingPlayerListAdapter(this, rosterList)
        recyclerView.adapter = rosterPlayerAdapter
    }

    fun onDoneAddingExistingPlayers(view: View) {
        val intent = Intent(this, FillYourRoster::class.java)
        intent.putParcelableArrayListExtra("ROSTER", rosterList)
        startActivity(intent)
    }
}
