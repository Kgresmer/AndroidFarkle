package com.gresmer.farklescoreboard.ExistingPlayers

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
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
        val existingPlayer = existingPlayers[position]


        setListItemBackgroundColor(existingPlayer, holder.linearLayout)
        holder.linearLayout.setOnClickListener({linearlayout -> addOrDropPlayer(linearlayout as LinearLayout, existingPlayer)})
        holder.nameTextView.text = existingPlayer.name
        holder.winsAndLossesTextView.text = "Wins: " + existingPlayer.wins + " | Losses: " + existingPlayer.losses
        holder.bestScoreTextView.text = "Best Score: " + existingPlayer.bestScore
    }

    private fun addOrDropPlayer(linearlayout: LinearLayout, existingPlayer: RosterPlayer) {
        existingPlayer.isOnRoster = !existingPlayer.isOnRoster
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

    init {
        nameTextView = itemView.findViewById(R.id.playerName)
        winsAndLossesTextView = itemView.findViewById(R.id.winsAndLosses)
        bestScoreTextView = itemView.findViewById(R.id.bestScore)
        linearLayout = itemView.findViewById(R.id.existing_player_list_item)
    }
}
