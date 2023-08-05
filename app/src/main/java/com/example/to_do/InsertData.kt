package com.example.to_do

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_insert_data.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class InsertData : AppCompatActivity() {
    private lateinit var database: myDataBase
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)

        database = Room.databaseBuilder(applicationContext, myDataBase::class.java, "TODO").build()

        savebtn.setOnClickListener {
            if (taskfield.text.toString().trim { it <= ' ' }
                    .isNotEmpty() && priorityfield.text.toString().trim { it <= ' ' }
                    .isNotEmpty()) {
                val task = taskfield.getText().toString()
                val priority = priorityfield.getText().toString()

                val calendar = Calendar.getInstance().time
                val date = DateFormat.getDateInstance().format(calendar)
                val formatter = DateTimeFormatter.ofPattern("hh:mm a")
                val time =  LocalTime.now().format(formatter).toString()

                GlobalScope.launch {

                    database.dao().insertTask(Entity(0, task, priority,time,date))
                    dataObject.setData(task, priority)
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
        }


    }
}