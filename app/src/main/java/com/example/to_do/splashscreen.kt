package com.example.to_do

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class splashscreen : AppCompatActivity() {
    private lateinit var database: myDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        database = Room.databaseBuilder(applicationContext, myDataBase::class.java, "TODO").build()
        GlobalScope.launch {
          dataObject.listdata =  database.dao().getTask() as MutableList<CardInfo>
        }
        startHeavyTask()
    }

    private fun startHeavyTask() {
        LongOperation().execute()
    }

    private open inner class LongOperation : AsyncTask<String?,Void?,String?>(){
        override fun doInBackground(vararg p0: String?): String? {
            for(i in 0.. 6){

                try {
                    Thread.sleep(1000)
                }catch (e:Exception){
                    Thread.interrupted()
                }
            }
            return "result"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val intent = Intent(this@splashscreen,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}