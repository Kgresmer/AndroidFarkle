package com.gresmer.farklescoreboard.roster

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import com.gresmer.farklescoreboard.R

import android.app.AlertDialog
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.gresmer.farklescoreboard.ExistingPlayers.ExistingPlayersList
import java.util.*


class FillYourRoster : AppCompatActivity() {

    var rosterList = ArrayList<RosterPlayer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_your_roster)
        val data = getIntent().getExtras()
        if (data != null) rosterList = data.getParcelableArrayList("ROSTER")
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
            if (nameInput.text.length > 0) {
                rosterList.add(RosterPlayer(Date().time, nameInput.text.toString(), 0, 0, 0, true))
                renderRecyclerRosterView()
                customDialog.dismiss()
            } else {
                Toast.makeText(baseContext, "Invalid", Toast.LENGTH_SHORT)
            }
        })
        cancelButton.setOnClickListener({
            customDialog.dismiss()
        })
    }

    fun renderRecyclerRosterView() {
        val rosterPlayerAdapter: RosterPlayerAdapter

        val recyclerView = findViewById<RecyclerView>(R.id.recycle_view)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val filteredList = rosterList.filter { it.isOnRoster }

        rosterPlayerAdapter = RosterPlayerAdapter(this, filteredList)
        recyclerView.adapter = rosterPlayerAdapter
    }

}
