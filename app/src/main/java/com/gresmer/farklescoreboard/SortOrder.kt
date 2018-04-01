package com.gresmer.farklescoreboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gresmer.farklescoreboard.roster.RosterPlayer
import java.util.ArrayList

class SortOrder : AppCompatActivity() {

    var rosterList = ArrayList<RosterPlayer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort_order)

        val data = getIntent().getExtras()
        if (data != null) rosterList = data.getParcelableArrayList("ROSTER")
    }
}
