package ru.otus.demo.saveactivityinstance

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ru.otus.demo.saveactivityinstance.MainActivity.Companion.ANSWER_TO_THE_ULTIMATE_QUESTION

class ActivityExplicitIntent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_explicit)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val intent = Intent()
        intent.putExtra(ANSWER_TO_THE_ULTIMATE_QUESTION, "42")
        setResult(Activity.RESULT_OK, intent)

        // getting value by "key"
        val value = getIntent().getParcelableExtra<PassData>("key")
        Log.i("ActivityExplicitIntent", "value$value")

    }
}