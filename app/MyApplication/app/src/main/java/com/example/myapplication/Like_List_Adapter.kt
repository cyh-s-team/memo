package com.example.myapplication.PepareClass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class Like_List_Adapter(val conList: List<Like_List>) :
        RecyclerView.Adapter<Like_List_Adapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val conImage: ImageView = view.findViewById(R.id.like_image)
        val conImage1: ImageView = view.findViewById(R.id.like_info)
        val conName: TextView = view.findViewById(R.id.like_name)
        val time: TextView = view.findViewById(R.id.like_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.like_list_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val con = conList[position]
        holder.conImage.setImageResource(con.imageID)
        holder.conImage1.setImageResource(con.imageID1)
        holder.conName.text = con.name
        holder.time.text = con.time

    }

    override fun getItemCount() = conList.size


}