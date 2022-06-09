package com.example.myapplication.PepareClass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class NewFollow_List_Adapter(val conList: List<NewFollow_List>) : RecyclerView.Adapter<NewFollow_List_Adapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val conImage: ImageView = view.findViewById(R.id.Follows_imageView)
        val conName: TextView = view.findViewById(R.id.Follows_name)
        val signature: TextView =view.findViewById(R.id.Follows_signature)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.newfollow_list, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val con = conList[position]
        holder.conImage.setImageResource(con.imageID)
        holder.conName.text=con.name
        holder.signature.text=con.signature

    }
    override fun getItemCount() = conList.size


}