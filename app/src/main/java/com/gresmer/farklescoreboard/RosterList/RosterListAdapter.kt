package com.gresmer.farklescoreboard.RosterList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.gresmer.farklescoreboard.R
import com.gresmer.farklescoreboard.RosterPlayer

/**
 * Binds Data to the view
 */

class RosterPlayerAdapter(private val context: Context, private val rosterPlayers: List<RosterPlayer>) : RecyclerView.Adapter<RosterPlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RosterPlayerViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.roster_list_item, parent, false)
        val parentRView = parent as RecyclerView
        view.setLayoutParams(RecyclerView.LayoutParams(parentRView.getLayoutManager().getWidth(), RecyclerView.LayoutParams.WRAP_CONTENT))
        return RosterPlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RosterPlayerViewHolder, position: Int) {

        val rosterPlayer = rosterPlayers[position]

        holder.nameTextView.text = rosterPlayer.name
        holder.winsAndLossesTextView.text = "Wins: " + rosterPlayer.wins + " | Losses: " + rosterPlayer.losses
        holder.bestScoreTextView.text = "Best Score: " + rosterPlayer.bestScore
    }

    override fun getItemCount(): Int {
        return rosterPlayers.size
    }

}

class RosterPlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var nameTextView: TextView
    var winsAndLossesTextView: TextView
    var bestScoreTextView: TextView

    init {
        nameTextView = itemView.findViewById(R.id.playerName)
        winsAndLossesTextView = itemView.findViewById(R.id.winsAndLosses)
        bestScoreTextView = itemView.findViewById(R.id.bestScore)
    }
}
