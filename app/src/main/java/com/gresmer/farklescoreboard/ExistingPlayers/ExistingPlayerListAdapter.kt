package com.gresmer.farklescoreboard.ExistingPlayers

import android.app.AlertDialog
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gresmer.farklescoreboard.R
import com.gresmer.farklescoreboard.roster.RosterPlayer
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Binds Data to the view
 */

class ExistingPlayerListAdapter(private val context: Context, private val existingPlayers: MutableList<RosterPlayer>) : RecyclerView.Adapter<ExistingPlayerViewHolder>() {

    private val clickSubject = PublishSubject.create<RosterPlayer>()
    private val clickDeleteSubject = PublishSubject.create<RosterPlayer>()
    val clickEvent: Observable<RosterPlayer> = clickSubject
    val clickDeleteEvent: Observable<RosterPlayer> = clickDeleteSubject
    val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExistingPlayerViewHolder {
        val view = inflater.inflate(R.layout.existing_player_list_item, parent, false)
        val parentRView = parent as RecyclerView
        view.setLayoutParams(RecyclerView.LayoutParams(parentRView.getLayoutManager().getWidth(), RecyclerView.LayoutParams.WRAP_CONTENT))
        return ExistingPlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExistingPlayerViewHolder, position: Int) {
        val existingPlayer = existingPlayers[position]

        setListItemBackgroundColor(existingPlayer, holder.linearLayout)
        holder.editButton.setOnClickListener({button -> editPlayer(button as Button, existingPlayer)})
        holder.linearLayout.setOnClickListener({linearlayout -> addOrDropPlayer(linearlayout as LinearLayout, existingPlayer)})
        holder.nameTextView.text = existingPlayer.name
        holder.winsAndLossesTextView.text = "Wins: " + existingPlayer.wins + " | Losses: " + existingPlayer.losses
        holder.bestScoreTextView.text = "Best Score: " + existingPlayer.bestScore
    }

    private fun editPlayer(button: Button, existingPlayer: RosterPlayer) {
        val dialog = AlertDialog.Builder(context)
        val dialogView = inflater.inflate(R.layout.edit_player, null)
        val nameInput = dialogView.findViewById<EditText>(R.id.player_name_input)
        nameInput.setText(existingPlayer.name)
        val editButton = dialogView.findViewById<Button>(R.id.edit_player_button)
        val cancelButton = dialogView.findViewById<Button>(R.id.cancel_edit_player_button)
        val deleteButton = dialogView.findViewById<Button>(R.id.delete_player_button)
        dialog.setView(dialogView)
        val customDialog = dialog.create()
        customDialog.show()

        editButton.setOnClickListener({
            if (nameInput.text.length > 0 && nameInput.text.length < 15) {
                if (checkIfNameIsTaken(nameInput.text.toString())) {
                    existingPlayer.name = nameInput.text.toString()
                    clickSubject.onNext(existingPlayer)
                    customDialog.dismiss()
                } else {
                    Toast.makeText(context, "That name is already taken", Toast.LENGTH_LONG).show()
                }
            } else {
                if (nameInput.text.isEmpty())
                    Toast.makeText(context, "You can't have a blank name", Toast.LENGTH_LONG).show()
                else if (nameInput.text.length > 14)
                    Toast.makeText(context, "That name is too long!", Toast.LENGTH_LONG).show()
            }
        })
        cancelButton.setOnClickListener({
            customDialog.dismiss()
        })

        deleteButton.setOnClickListener({
            val deleteDialog = AlertDialog.Builder(context)
            val deleteDialogView = inflater.inflate(R.layout.delete_player, null)
            val cancelDeletePlayerButton = deleteDialogView.findViewById<Button>(R.id.cancel_delete_player_button)
            val deletePlayerButton = deleteDialogView.findViewById<Button>(R.id.delete_player_button)
            deleteDialog.setView(deleteDialogView)
            val customDeleteDialog = deleteDialog.create()
            customDeleteDialog.show()

            cancelDeletePlayerButton.setOnClickListener({
                customDeleteDialog.dismiss()
            })

            deletePlayerButton.setOnClickListener({
                existingPlayers.remove(existingPlayer)
                clickDeleteSubject.onNext(existingPlayer)
                customDeleteDialog.dismiss()
                customDialog.dismiss()
            })

        })
    }

    private fun checkIfNameIsTaken(name: String): Boolean {
        val potentialExistingPlayer = existingPlayers.find { it.name.toLowerCase() == name.toLowerCase() }
        return potentialExistingPlayer == null
    }

    private fun addOrDropPlayer(linearlayout: LinearLayout, existingPlayer: RosterPlayer) {
        existingPlayer.isOnRoster = !existingPlayer.isOnRoster
        clickSubject.onNext(existingPlayer)
        setListItemBackgroundColor(existingPlayer, linearlayout)
    }

    private fun setListItemBackgroundColor(existingPlayer: RosterPlayer, linearlayout: LinearLayout) {
        if (existingPlayer.isOnRoster) {
            linearlayout.setBackgroundColor(ContextCompat.getColor(context, R.color.orange))
        } else {
            linearlayout.setBackgroundColor(ContextCompat.getColor(context, R.color.lightTeal))
        }
    }

    override fun getItemCount(): Int {
        return existingPlayers.size
    }

}

class ExistingPlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var nameTextView: TextView
    var winsAndLossesTextView: TextView
    var bestScoreTextView: TextView
    var linearLayout: LinearLayout
    var editButton: Button

    init {
        nameTextView = itemView.findViewById(R.id.playerName)
        winsAndLossesTextView = itemView.findViewById(R.id.winsAndLosses)
        bestScoreTextView = itemView.findViewById(R.id.bestScore)
        linearLayout = itemView.findViewById(R.id.existing_player_list_item)
        editButton = itemView.findViewById(R.id.editExistingPlayerButton)
    }
}
