package com.gresmer.farklescoreboard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.EditText
import com.gresmer.farklescoreboard.roster.FillYourRoster
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, FillYourRoster::class.java)
        val timer = Timer("schedule", true);
        timer.schedule( 2500) {
            startActivity(intent)
        }
    }

}
