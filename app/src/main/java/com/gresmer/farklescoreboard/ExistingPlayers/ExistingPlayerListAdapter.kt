package com.gresmer.farklescoreboard.ExistingPlayers

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.gresmer.farklescoreboard.R
import com.gresmer.farklescoreboard.roster.RosterPlayer

/**
 * Binds Data to the view
 */

class ExistingPlayerListAdapter(private val context: Context, private val existingPlayers: List<RosterPlayer>) : RecyclerView.Adapter<ExistingPlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExistingPlayerViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.existing_player_list_item, parent, false)
        val parentRView = parent as RecyclerView
        view.setLayoutParams(RecyclerView.LayoutParams(parentRView.getLayoutManager().getWidth(), RecyclerView.LayoutParams.WRAP_CONTENT))
        return ExistingPlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExistingPlayerViewHolder, position: Int) {
        val rosterPlayer = existingPlayers[position]

        holder.dropButton.visibility = if (rosterPlayer.isOnRoster) View.VISIBLE else View.GONE
        holder.addButton.visibility = if (!rosterPlayer.isOnRoster) View.VISIBLE else View.GONE
        holder.nameTextView.text = rosterPlayer.name
        holder.winsAndLossesTextView.text = "Wins: " + rosterPlayer.wins + " | Losses: " + rosterPlayer.losses
        holder.bestScoreTextView.text = "Best Score: " + rosterPlayer.bestScore
    }

    override fun getItemCount(): Int {
        return existingPlayers.size
    }

}

class ExistingPlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var nameTextView: TextView
    var winsAndLossesTextView: TextView
    var bestScoreTextView: TextView
    var addButton: Button
    var dropButton: Button

    init {
        nameTextView = itemView.findViewById(R.id.playerName)
        winsAndLossesTextView = itemView.findViewById(R.id.winsAndLosses)
        bestScoreTextView = itemView.findViewById(R.id.bestScore)
        addButton = itemView.findViewById(R.id.addExistingPlayerToRosterButton)
        dropButton = itemView.findViewById(R.id.dropExistingPlayerToRosterButton)
    }
}
