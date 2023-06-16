package com.example.brightme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brightme.data.Doctor

class ActivityAdapter (private val doctorList : ArrayList <Doctor>) : RecyclerView.Adapter<ActivityAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_doctor, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = doctorList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.titleDoctor.text = currentItem.title
        holder.rating.text = currentItem.rating
        holder.online.text = currentItem.online
        holder.years.text = currentItem.years
        holder.price.text = currentItem.price

    }

    override fun getItemCount(): Int {
        return doctorList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titleImage : ImageView = itemView.findViewById(R.id.iv_doctor)
        val titleDoctor : TextView = itemView.findViewById(R.id.tv_doctor)
        val rating : TextView = itemView.findViewById(R.id.tv_rating)
        val online : TextView = itemView.findViewById(R.id.tv_status)
        val years : TextView = itemView.findViewById(R.id.tv_years)
        val price : TextView = itemView.findViewById(R.id.tv_price)

    }

}