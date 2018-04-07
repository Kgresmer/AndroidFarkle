package com.gresmer.farklescoreboard.SortOrder

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.gresmer.farklescoreboard.R
import com.gresmer.farklescoreboard.RosterPlayer
import com.gresmer.farklescoreboard.Scoreboard.Scoreboard
import java.util.ArrayList

class SortOrderActivity : AppCompatActivity() {

    var rosterList = ArrayList<RosterPlayer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort_order)

        val data = getIntent().getExtras()
        if (data != null) rosterList = data.getParcelableArrayList("ROSTER")

        renderRecyclerRosterView()
    }

    fun renderRecyclerRosterView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycle_view)
        val filteredList = rosterList.filter { it.isOnRoster }
        val rosterPlayerAdapter: SortOrderListAdapter

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        rosterPlayerAdapter = SortOrderListAdapter(this, filteredList as MutableList<RosterPlayer>)
        val itemTouchHelperCallback = ItemTouchHelperCallback(rosterPlayerAdapter)
        val touchHelper = ItemTouchHelper(itemTouchHelperCallback)
        touchHelper.attachToRecyclerView(recyclerView)
        recyclerView.adapter = rosterPlayerAdapter
    }

    fun onReadyButtonClick(view: View) {
        val intent = Intent(this, Scoreboard::class.java)
        intent.putParcelableArrayListExtra("ROSTER", rosterList)
        startActivity(intent)
    }
}
