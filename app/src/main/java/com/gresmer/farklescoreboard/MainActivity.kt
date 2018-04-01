package com.gresmer.farklescoreboard

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.gresmer.farklescoreboard.ExistingPlayers.ExistingPlayersList
import com.gresmer.farklescoreboard.roster.FillYourRoster
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onPlayerStatsClicked(view:  View) {
        val intent = Intent(this, ExistingPlayersList::class.java)
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
        val intent = Intent(this, FillYourRoster::class.java)
        startActivity(intent)
    }

}


