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
        dialog.setView(dialogView)
        dialog.setPositiveButton("Add", { dialogInterface: DialogInterface, i: Int -> });
        dialog.setNegativeButton("Cancel", { dialogInterface: DialogInterface, i: Int -> });
        val customDialog = dialog.create()
        customDialog.show()
        val darkerTealColor = ContextCompat.getColor(this, R.color.darkerTeal)
        val orangeColor = ContextCompat.getColor(this, R.color.orange)
        val whiteColor = ContextCompat.getColor(this, R.color.white)

        val addButton = customDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        addButton.setBackgroundColor(darkerTealColor)
        addButton.setTextColor(whiteColor)
        addButton.setTextSize(18f)
        addButton.setOnClickListener({
            if (nameInput.text.length > 0) {
                rosterList.add(RosterPlayer(Date().time, nameInput.text.toString(), 0, 0, 0))
                renderRecyclerRosterView()
                customDialog.dismiss()
            } else {
                Toast.makeText(baseContext, "Invalid", Toast.LENGTH_SHORT)
            }
        })

        val cancelButton = customDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        cancelButton.setBackgroundColor(orangeColor)
        cancelButton.setTextColor(whiteColor)
        cancelButton.setPadding(4,0,8,0)
        cancelButton.setTextSize(18f)
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