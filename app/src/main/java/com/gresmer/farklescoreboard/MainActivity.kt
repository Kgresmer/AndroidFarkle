package com.gresmer.farklescoreboard

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.gresmer.farklescoreboard.ExistingPlayers.ExistingPlayersListActivity
import com.gresmer.farklescoreboard.RosterList.RosterListActivity
import java.io.File
import java.io.ObjectInputStream
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    var rosterList = ArrayList<RosterPlayer>()
    val FILE_NAME = "playerData";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
    }

    fun onPlayerStatsClicked(view:  View) {
        val intent = Intent(this, ExistingPlayersListActivity::class.java)
        intent.putParcelableArrayListExtra("ROSTER", rosterList)
        startActivity(intent)
    }

    fun onRulesClicked(view:  View) {
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.display_rules_dialog, null)
        val closeRulesTopButton = dialogView.findViewById<Button>(R.id.close_rules_title_button)
        dialog.setView(dialogView)
        val customDialog = dialog.create()
        customDialog.show()

        closeRulesTopButton.setOnClickListener({
            customDialog.dismiss()
        })
    }

    fun onScoreAGameClicked(view:  View) {
        val intent = Intent(this, RosterListActivity::class.java)
        intent.putParcelableArrayListExtra("ROSTER", rosterList)
        startActivity(intent)
    }

}


