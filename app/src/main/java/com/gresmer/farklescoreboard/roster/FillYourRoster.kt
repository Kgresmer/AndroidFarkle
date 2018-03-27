package com.gresmer.farklescoreboard.roster

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import com.gresmer.farklescoreboard.R

import android.app.AlertDialog
import android.content.DialogInterface
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*


/**
 * Created by kgresmer on 3/18/18.
 */

class FillYourRoster : AppCompatActivity() {

    val rosterList = ArrayList<RosterPlayer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_your_roster)

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
                rosterList.add(RosterPlayer(Date().time, nameInput.text.toString(), 0, 0, 0))
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

        rosterPlayerAdapter = RosterPlayerAdapter(this, rosterList)
        recyclerView.adapter = rosterPlayerAdapter
    }

}

//class AddPlayerDialogFragment : DialogFragment() {
//    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
//
//        val builder = AlertDialog.Builder(getActivity())
//        // Get the layout inflater
//        val inflater = getActivity().getLayoutInflater()
//
//        // Inflate and set the layout for the dialog
//        // Pass null as the parent view because its going in the dialog layout
//        builder.setView(inflater.inflate(R.layout.add_player, null))
//                // Add action buttons
//                .setPositiveButton(R.string.add_button, DialogInterface.OnClickListener { dialog, id ->
//                    // sign in the user ...
//                })
//                .setNegativeButton(R.string.cancel_button, DialogInterface.OnClickListener { dialog, id -> this@AddPlayerDialogFragment.getDialog().cancel() })
//        return builder.create()
//    }
//}