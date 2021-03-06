package com.gresmer.farklescoreboard.RosterList

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import com.gresmer.farklescoreboard.R

import android.app.AlertDialog
import android.content.Context
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.gresmer.farklescoreboard.ExistingPlayers.ExistingPlayersListActivity
import com.gresmer.farklescoreboard.RosterPlayer
import com.gresmer.farklescoreboard.SortOrder.SortOrderActivity
import java.io.*
import java.util.*


class RosterListActivity : AppCompatActivity() {

    var rosterList = ArrayList<RosterPlayer>()
    val FILE_NAME = "playerData";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_your_roster)

        val data = getIntent().getExtras()
        if (data != null) rosterList = data.getParcelableArrayList("ROSTER")
        print("back to roster " + rosterList)
        renderRecyclerRosterView()
    }
    
    fun onAddExistingPlayer(view: View) {
        val intent = Intent(this, ExistingPlayersListActivity::class.java)
        intent.putParcelableArrayListExtra("ROSTER", rosterList)
        startActivity(intent)
    }

    fun onAddNewPlayer(view: View) {
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.add_player, null)
        val nameInput = dialogView.findViewById<EditText>(R.id.player_name_input)
        val addButton = dialogView.findViewById<Button>(R.id.add_player_button)
        val cancelButton = dialogView.findViewById<Button>(R.id.cancel_add_player_button)
        dialog.setView(dialogView)
        val customDialog = dialog.create()
        customDialog.show()

        addButton.setOnClickListener({
            if (nameInput.text.length > 0 && nameInput.text.length < 15) {
                if (checkIfNameIsTaken(nameInput.text.toString())) {
                    rosterList.add(RosterPlayer(Date().time, nameInput.text.toString(), 0, 0, 0, true))
                    renderRecyclerRosterView()
                    customDialog.dismiss()
                    saveRosterData()
                } else {
                    Toast.makeText(this, "That name is already taken", Toast.LENGTH_LONG).show()
                }
            } else {
                if (nameInput.text.isEmpty())
                    Toast.makeText(baseContext, "You can't have a blank name", Toast.LENGTH_LONG).show()
                else if (nameInput.text.length >= 15)
                    Toast.makeText(baseContext, "That name is too long!", Toast.LENGTH_LONG).show()
            }
        })
        cancelButton.setOnClickListener({
            customDialog.dismiss()
        })
    }

    private fun checkIfNameIsTaken(name: String): Boolean {
        val potentialExistingPlayer = rosterList.find { it.name.toLowerCase() == name.toLowerCase() }
        return potentialExistingPlayer == null
    }

    fun renderRecyclerRosterView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycle_view)
        val emptyRosterBanner = findViewById<TextView>(R.id.roster_empty_banner)
        val emptyRosterInstructionsBanner = findViewById<TextView>(R.id.instructions_banner)
        val readyButton = findViewById<Button>(R.id.ready_button)
        val existingPlayersButton = findViewById<Button>(R.id.existing_player_button)

        existingPlayersButton.isEnabled = !rosterList.isEmpty()
        if (existingPlayersButton.isEnabled) {
            existingPlayersButton.setBackgroundColor(ContextCompat.getColor(this, R.color.teal))
        } else {
            existingPlayersButton.setBackgroundColor(ContextCompat.getColor(this, R.color.lightTeal))
        }

        val filteredList = rosterList.filter { it.isOnRoster }

        if (filteredList.isNotEmpty()) {
            recyclerView.visibility = View.VISIBLE
            emptyRosterBanner.visibility = View.INVISIBLE
            emptyRosterInstructionsBanner.visibility = View.INVISIBLE
            readyButton.visibility = View.VISIBLE

            val rosterPlayerAdapter: RosterPlayerAdapter

            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(this)
            rosterPlayerAdapter = RosterPlayerAdapter(this, filteredList)
            recyclerView.adapter = rosterPlayerAdapter
        } else {
            recyclerView.visibility = View.INVISIBLE
            emptyRosterBanner.visibility = View.VISIBLE
            emptyRosterInstructionsBanner.visibility = View.VISIBLE
            readyButton.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        // disable
    }

    fun onReadyButtonClick(view: View) {
        saveRosterData()
        val intent = Intent(this, SortOrderActivity::class.java)
        intent.putParcelableArrayListExtra("ROSTER", rosterList)
        startActivity(intent)
    }

    private fun saveRosterData() {
        this.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
            val objectOuputStream = ObjectOutputStream(it)
            for (player in rosterList) {
                objectOuputStream.writeObject(player)
            }
        }
    }

}