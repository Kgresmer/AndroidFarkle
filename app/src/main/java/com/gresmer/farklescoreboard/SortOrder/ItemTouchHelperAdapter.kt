package com.gresmer.farklescoreboard.SortOrder

interface ItemTouchHelperAdapter {

    fun onItemMoved(fromPosition: Int, toPosition: Int)

    fun onItemDismiss(position: Int)

}
