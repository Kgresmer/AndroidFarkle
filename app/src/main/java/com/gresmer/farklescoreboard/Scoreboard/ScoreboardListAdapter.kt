package com.gresmer.farklescoreboard.Scoreboard

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.gresmer.farklescoreboard.RosterPlayer

class ScoreboardListAdapter(private val context: Context, private val rosterPlayers: List<RosterPlayer>) : RecyclerView.Adapter<ScoreboardListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ScoreboardListViewHolder {

    }

    override fun onBindViewHolder(holder: ?, position: Int) {

    }

    override fun getItemCount(): Int {
        return rosterPlayers.size
    }

}

class ScoreboardListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


}