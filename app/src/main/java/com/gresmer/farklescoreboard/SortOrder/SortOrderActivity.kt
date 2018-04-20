package com.gresmer.farklescoreboard.SortOrder

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.TOUCH_SLOP_PAGING
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.MenuItem
import android.view.View
import com.gresmer.farklescoreboard.R
import com.gresmer.farklescoreboard.RosterList.RosterListActivity
import com.gresmer.farklescoreboard.RosterPlayer
import com.gresmer.farklescoreboard.Scoreboard.Scoreboard
import java.util.*

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

        recyclerView.setScrollingTouchSlop(TOUCH_SLOP_PAGING)
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

    override fun onBackPressed() {
        val intent = Intent(this, RosterListActivity::class.java)
        intent.putParcelableArrayListExtra("ROSTER", rosterList)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId().equals(android.R.id.home)) {
            onBackPressed()
            return true
        }
        return false
    }
}
