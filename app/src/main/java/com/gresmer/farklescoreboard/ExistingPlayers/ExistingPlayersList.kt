package com.gresmer.farklescoreboard.ExistingPlayers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TableRow
import android.widget.Toast
import com.gresmer.farklescoreboard.R
import com.gresmer.farklescoreboard.roster.FillYourRoster
import com.gresmer.farklescoreboard.roster.RosterPlayer
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class ExistingPlayersList : AppCompatActivity() {

    private var playerList = ArrayList<RosterPlayer>()
    private var subscribe: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_existing_players_list)
        val data = getIntent().getExtras()
        if (data != null) playerList = data.getParcelableArrayList("ROSTER")
        setDoneButtonBackgroundColor(playerList)
        renderExistingPlayerRecyclerView()

    }

    private fun setDoneButtonBackgroundColor(playerList: ArrayList<RosterPlayer>) {
        val doneButton = findViewById<Button>(R.id.doneButton)
        for (i in 0..playerList.size - 1) {
            if (playerList.get(i).isOnRoster) {
                doneButton.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))
                return
            }
        }
        doneButton.setBackgroundColor(ContextCompat.getColor(this, R.color.lightTeal))
    }

    fun renderExistingPlayerRecyclerView() {

        val recyclerView = findViewById<RecyclerView>(R.id.recycle_view)


        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        var rosterPlayerAdapter = ExistingPlayerListAdapter(this, playerList)
        recyclerView.adapter = rosterPlayerAdapter

        setupItemClick(rosterPlayerAdapter)
    }

    private fun setupItemClick(rosterPlayerAdapter: ExistingPlayerListAdapter) {
        subscribe = rosterPlayerAdapter.clickEvent
                .subscribe({
                    p -> updatePlayerList(p)
                })
    }

    private fun updatePlayerList(player: RosterPlayer?) {
        val doneButton = findViewById<Button>(R.id.doneButton)
        var someoneIsOnRoster = false;

        for (i in 0..playerList.size - 1) {

            if (playerList.get(i).id == player?.id) {
                playerList.get(i).isOnRoster = player?.isOnRoster
            }

            if (playerList.get(i).isOnRoster) {
                someoneIsOnRoster = true;
            }
        }

        if (someoneIsOnRoster)
            doneButton.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))
        else
            doneButton.setBackgroundColor(ContextCompat.getColor(this, R.color.lightTeal))
    }

    fun onDoneAddingExistingPlayers(view: View) {
        val intent = Intent(this, FillYourRoster::class.java)
        intent.putParcelableArrayListExtra("ROSTER", playerList)
        startActivity(intent)
    }
}
