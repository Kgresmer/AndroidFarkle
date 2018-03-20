package com.gresmer.farklescoreboard.roster

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.gresmer.farklescoreboard.R

/**
 * Binds Data to the view
 */

class RosterPlayerAdapter(private val context: Context, private val rosterPlayers: List<RosterPlayer>) : RecyclerView.Adapter<RosterPlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RosterPlayerViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.roster_list_item, null)
        return RosterPlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RosterPlayerViewHolder, position: Int) {
        val rosterPlayer = rosterPlayers[position]

//        holder.nameTextView.text = rosterPlayer.name
//        nameholder.winsTextView.text = rosterPlayer.wins
        // for an  image
        //  this method is deprecated
        // holder.imageView.setImageDrawable(context.getResources().getDrawable(imageId));
    }

    override fun getItemCount(): Int {
        return rosterPlayers.size
    }

}

class RosterPlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    init {
    }
}
