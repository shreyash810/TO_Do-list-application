package com.example.to_do

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_update_delete.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class UpdateDelete : AppCompatActivity() {
    private lateinit var database: myDataBase
    private lateinit var taskview : TextView
    private lateinit var priorityview : TextView
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)

        database = Room.databaseBuilder(applicationContext, myDataBase::class.java, "TODO").build()

        taskview = findViewById(R.id.taskupd)
        priorityview = findViewById(R.id.priorityupd)

        val pos = intent.getIntExtra("id", -1)



        if (pos != -1) {
            val tasku = dataObject.getdata(pos).task
            val priou = dataObject.getdata(pos).priority

            taskupd.setText(tasku)
            priorityupd.setText(priou)



            deletebtn.setOnClickListener {
                dataObject.delete(pos)
                val calendar = Calendar.getInstance().time
                val date = DateFormat.getDateInstance().format(calendar)
                val formatter = DateTimeFormatter.ofPattern("hh:mm a")
                val time =  LocalTime.now().format(formatter).toString()
                GlobalScope.launch {

                    database.dao().deleteTask(Entity(pos+1,taskview.text.toString(),priorityview.text.toString(),time,date))

                }

                myintent()
            }
            updatebtn.setOnClickListener {
                dataObject.updatedata(pos,taskview.text.toString(),priorityview.text.toString())
                val calendar = Calendar.getInstance().time
                val date = DateFormat.getDateInstance().format(calendar)
                val formatter = DateTimeFormatter.ofPattern("hh:mm a")
                val time =  LocalTime.now().format(formatter).toString()
                GlobalScope.launch {

                    database.dao().updateTask(Entity(pos+1,taskview.text.toString(),priorityview.text.toString(),time,date))

                }
                Toast.makeText(this,"Updated",Toast.LENGTH_LONG).show()
                myintent()
            }


        }

    }

     fun myintent() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}