package com.gresmer.farklescoreboard

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.gresmer.farklescoreboard.ExistingPlayers.ExistingPlayersListActivity
import com.gresmer.farklescoreboard.RosterList.RosterListActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onPlayerStatsClicked(view:  View) {
        val intent = Intent(this, ExistingPlayersListActivity::class.java)
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
        startActivity(intent)
    }

}


