package com.gresmer.farklescoreboard.ExistingPlayers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.gresmer.farklescoreboard.R
import com.gresmer.farklescoreboard.RosterList.RosterListActivity
import com.gresmer.farklescoreboard.RosterPlayer
import io.reactivex.disposables.Disposable
import kotlin.collections.ArrayList

class ExistingPlayersListActivity : AppCompatActivity() {

    private var playerList: MutableList<RosterPlayer> = ArrayList<RosterPlayer>()
    private var playerChangeSubscription: Disposable? = null
    private var playerDeleteSubscription: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_existing_players_list)
        val data = getIntent().getExtras()
        if (data != null) playerList = data.getParcelableArrayList("ROSTER")
        setDoneButtonBackgroundColor(playerList)
        renderExistingPlayerRecyclerView()

    }

    private fun setDoneButtonBackgroundColor(playerList: MutableList<RosterPlayer>) {
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
        playerChangeSubscription = rosterPlayerAdapter.clickEvent
                .subscribe({
                    p -> updatePlayerList(p, false)
                })
        playerDeleteSubscription = rosterPlayerAdapter.clickDeleteEvent
                .subscribe({
                    p -> updatePlayerList(p, true)
                })
    }

    private fun updatePlayerList(player: RosterPlayer?, deletePlayer: Boolean) {
        val doneButton = findViewById<Button>(R.id.doneButton)
        var someoneIsOnRoster = false;

        if (deletePlayer) {
            playerList.remove(player)
        }

        for (i in 0..playerList.size - 1) {

            if (playerList.get(i).id == player?.id) {
                playerList.get(i).isOnRoster = player.isOnRoster
                playerList.get(i).name = player.name
            }

            if (playerList.get(i).isOnRoster) {
                someoneIsOnRoster = true;
            }
        }

        renderExistingPlayerRecyclerView()

        if (someoneIsOnRoster)
            doneButton.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))
        else
            doneButton.setBackgroundColor(ContextCompat.getColor(this, R.color.lightTeal))
    }

    fun onDoneAddingExistingPlayers(view: View) {
        val intent = Intent(this, RosterListActivity::class.java)
        intent.putParcelableArrayListExtra("ROSTER", ArrayList(playerList))
        startActivity(intent)
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Click 'Done' to return to roster", Toast.LENGTH_SHORT).show()
    }

}
