package com.example.brightme.ui.navigation.activity.glowup_plan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brightme.R

class GlowUpPlanAdapter (private val planList : ArrayList <GlowUpPlan>) : RecyclerView.Adapter<GlowUpPlanAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_plan, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = planList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.titleDate.text = currentItem.title
    }

    override fun getItemCount(): Int {
        return planList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titleImage : ImageView = itemView.findViewById(R.id.iv_face)
        val titleDate : TextView = itemView.findViewById(R.id.tv_face)
    }

}