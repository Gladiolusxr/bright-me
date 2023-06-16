package com.example.brightme.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brightme.R
import com.example.brightme.data.model.Education

class ListEducationAdapter(private val listEducation: ArrayList<Education>) : RecyclerView.Adapter<ListEducationAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvTitle: TextView = itemView.findViewById(R.id.title)
        val tvName: TextView = itemView.findViewById(R.id.doctor_name)
        val tvVideo: TextView = itemView.findViewById(R.id.btn_video)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_education, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, name, video, photo) = listEducation[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvTitle.text = title
        holder.tvName.text = name
        holder.tvVideo.text = video
    }

    override fun getItemCount(): Int = listEducation.size
}