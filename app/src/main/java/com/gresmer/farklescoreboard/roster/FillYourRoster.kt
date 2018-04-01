package com.gresmer.farklescoreboard.roster

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import com.gresmer.farklescoreboard.R

import android.app.AlertDialog
import android.content.Context
import android.opengl.Visibility
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.gresmer.farklescoreboard.ExistingPlayers.ExistingPlayersList
import java.io.*
import java.util.*


class FillYourRoster : AppCompatActivity() {

    var rosterList = ArrayList<RosterPlayer>()
    val FILE_NAME = "playerData";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_your_roster)

        val file = File(this.filesDir, FILE_NAME)
        try {
            File(this.filesDir, FILE_NAME).inputStream().use {
                val inputS = ObjectInputStream(it)
                var cont = true;
                while (cont) {
                    val player = inputS.readObject()
                    if (player != null)
                        rosterList.add(player as RosterPlayer)
                    else
                        cont = false
                }
            }
        } catch (e: Exception) {
            println(e.printStackTrace());
        }


        print(rosterList)

        val data = getIntent().getExtras()
        if (data != null) rosterList = data.getParcelableArrayList("ROSTER")
        print(rosterList)

        renderRecyclerRosterView()
    }
    
    fun onAddExistingPlayer(view: View) {
        val intent = Intent(this, ExistingPlayersList::class.java)
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
                rosterList.add(RosterPlayer(Date().time, nameInput.text.toString(), 0, 0, 0, true))
                renderRecyclerRosterView()
                customDialog.dismiss()
                saveRosterData()
            } else {
                if (nameInput.text.isEmpty())
                    Toast.makeText(baseContext, "You can't have a blank name", Toast.LENGTH_LONG).show()
                else if (nameInput.text.length > 14)
                    Toast.makeText(baseContext, "That name is too long!", Toast.LENGTH_LONG).show()
            }
        })
        cancelButton.setOnClickListener({
            customDialog.dismiss()
        })
    }

    fun renderRecyclerRosterView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycle_view)
        val emptyRosterBanner = findViewById<TextView>(R.id.roster_empty_banner)
        val emptyRosterInstructionsBanner = findViewById<TextView>(R.id.instructions_banner)
        val readyButton = findViewById<Button>(R.id.ready_button)
        val existingPlayersButton = findViewById<Button>(R.id.existing_player_button)

        existingPlayersButton.isEnabled = !rosterList.isEmpty()
        if (existingPlayersButton.isEnabled) {
            existingPlayersButton.setBackgroundColor(ContextCompat.getColor(this, R.color.darkerTeal))
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
        println("saving to file")
        saveRosterData()
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
