package ru.otus.demo.saveactivityinstance

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    private lateinit var mMessageEditText: EditText

    private var storedValue: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)
        mMessageEditText = findViewById(R.id.message_edit_text)
        mMessageEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                storedValue = s.toString()
            }

        })
        savedInstanceState?.apply {
            storedValue = this.getString(MESS_KEY, "")
            mMessageEditText.setText(storedValue)
            Log.d(TAG, "onCreate:[$storedValue]")
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        storedValue = savedInstanceState.getString(MESS_KEY,"")
        mMessageEditText.setText(storedValue)
        Log.d(TAG, "onRestoreInstanceState:[$storedValue]")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(MESS_KEY, storedValue)
        Log.d(TAG, "onSaveInstanceState:[$storedValue]")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    fun clickExplicit(view: View?) {
        getTheAnswer()
    }

    fun clickImplicit(view: View?) { // Создаем  текстовое сообщение
        val textMessage = "Our message"
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage)
        sendIntent.type = "text/plain"
        val title = resources.getString(R.string.chooser_title)
        // Создаем Intent для отображения диалога выбора.
        val chooser = Intent.createChooser(sendIntent, title)
        // Проверяем, что intent может быть успешно обработан
        sendIntent.resolveActivity(packageManager)?.let {
            startActivity(chooser)
        }
    }


    private fun getTheAnswer() {
        // Passing value of "key"
        val intent = Intent(this@MainActivity, ActivityExplicitIntent::class.java)
        intent.putExtra("key", PassData("our desc"))
        startActivityForResult(intent, OUR_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == OUR_REQUEST_CODE) {
            var answer: String? = null
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    answer = it.getStringExtra(ANSWER_TO_THE_ULTIMATE_QUESTION)
                }
            }
            Log.i(TAG, "the answer is:$answer")
        }
    }


/*
    override fun onResume() {
        super.onResume()
        // Restoring storedValue
        Storage.message?.let {
            storedValue = it
            mMessageEditText.setText(it)
            Log.d(TAG, "onResume:[$storedValue]")
        }
    }

    override fun onPause() {
        super.onPause()
        // Saving storedValue
        Storage.message = storedValue
        Log.d(TAG, "onPause:[$storedValue]")
    }
*/


    companion object {
        val TAG = MainActivity::class.java.simpleName
        const val MESS_KEY = "saved_mess_key"
        const val OUR_REQUEST_CODE = 42
        const val ANSWER_TO_THE_ULTIMATE_QUESTION = "answer"
    }
}