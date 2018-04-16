package com.gresmer.farklescoreboard.SortOrder

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gresmer.farklescoreboard.R
import com.gresmer.farklescoreboard.RosterPlayer
import java.util.*

class SortOrderListAdapter(private val context: Context, private val rosterList: MutableList<RosterPlayer>) :
        RecyclerView.Adapter<SortOrderListViewHolder>(), ItemTouchHelperAdapter {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SortOrderListViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.sort_order_list_item, parent, false)
        val parentView = parent as RecyclerView
        val viewWidth: Int = (parentView.layoutManager.width * .95).toInt()
        view.setLayoutParams(RecyclerView.LayoutParams(viewWidth, RecyclerView.LayoutParams.WRAP_CONTENT))
        parent.offsetChildrenVertical(6)
        return SortOrderListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rosterList.size
    }

    override fun onBindViewHolder(holder: SortOrderListViewHolder, position: Int) {
        val rosterPlayer = rosterList[position]
        holder.nameTextView.text = rosterPlayer.name
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        println("on item moved" + fromPosition + " to " + toPosition)
        swapItems(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
//        rosterList.removeAt(position)
//        notifyItemRemoved(position)
    }

    private fun swapItems(positionFrom: Int, positionTo: Int) {
        println("on item moved" + positionFrom + " to " + positionTo)
        Collections.swap(rosterList, positionFrom, positionTo)
        notifyItemMoved(positionFrom, positionTo)
    }

    fun ViewGroup.inflate(layoutRes: Int):View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }
}

class SortOrderListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var nameTextView: TextView

    init {
        nameTextView = itemView.findViewById(R.id.playerName)
    }

}
