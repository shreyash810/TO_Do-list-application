package com.example.to_do

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.DataOutput

class MainActivity : AppCompatActivity() {
    private lateinit var database: myDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = Room.databaseBuilder(applicationContext, myDataBase::class.java, "TODO").build()
        add.setOnClickListener {
            val intent = Intent(this, InsertData::class.java)
            startActivity(intent)
        }
        deleteallbtn.setOnClickListener {
            dataObject.deleteall()
            GlobalScope.launch {

                database.dao().deleteAll()

            }
            render()
        }
        render()
    }
  private  fun render(){
        recycle_view.adapter = Adapter(dataObject.getAllData())
        recycle_view.layoutManager = LinearLayoutManager(this)
    }
}