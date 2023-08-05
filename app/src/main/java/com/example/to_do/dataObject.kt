package com.example.to_do

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.DateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*


object dataObject {
    var listdata = mutableListOf<CardInfo>()


    @SuppressLint("NewApi")
    fun setData(task: String, priority : String) {
        val calendar = Calendar.getInstance().time
        val date = DateFormat.getDateInstance().format(calendar)
        val formatter = DateTimeFormatter.ofPattern("hh:mm a")
        val time =  LocalTime.now().format(formatter).toString()
        listdata.add(CardInfo(task, priority,time,date))
    }

    fun getAllData(): List<CardInfo> {
        return listdata
    }

    fun getdata(pos:Int): CardInfo {
        return listdata[pos];
    }
    @SuppressLint("NewApi")
    fun updatedata(pos:Int,task: String,priority:String){
        var calendar = Calendar.getInstance().time
        var date = DateFormat.getDateInstance().format(calendar)
        val formatter = DateTimeFormatter.ofPattern("hh:mm a")
        val time =  LocalTime.now().format(formatter).toString()
        listdata[pos].task = task
        listdata[pos].priority = priority
        listdata[pos].time = time
        listdata[pos].date = date
    }

    fun delete(pos: Int){
        listdata.removeAt(pos)
    }

    fun deleteall(){
       listdata.clear()
    }
}