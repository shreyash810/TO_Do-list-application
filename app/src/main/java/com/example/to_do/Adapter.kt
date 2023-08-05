package com.example.to_do

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_viewdata.view.*
import java.text.DateFormat
import java.util.*

class Adapter(var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var task = itemView.task
        var priority = itemView.priority
        var bground = itemView.linearbg
        var time = itemView.time
        var date = itemView.date


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_viewdata, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when (data[position].priority.toLowerCase().trim{it <= ' '}) {

            "high" -> holder.bground.setBackgroundColor(Color.parseColor("#F05454"))
            "medium" -> holder.bground.setBackgroundColor(Color.parseColor("#EDC988"))
            else -> holder.bground.setBackgroundColor(Color.parseColor("#008080"))


        }

        holder.task.text = data[position].task
        holder.priority.text = data[position].priority
        holder.date.text = data[position].date
        holder.time.text = data[position].time

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateDelete::class.java)
            intent.putExtra("id", position)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


}