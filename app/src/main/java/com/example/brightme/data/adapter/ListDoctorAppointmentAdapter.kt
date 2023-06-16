package com.example.brightme.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brightme.R
import com.example.brightme.data.model.DoctorAppointment

class ListDoctorAppointmentAdapter(private val listDoctor: ArrayList<DoctorAppointment>) : RecyclerView.Adapter<ListDoctorAppointmentAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvNumber: TextView = itemView.findViewById(R.id.tv_item_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_doctor_appointment, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, number, photo) = listDoctor[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvNumber.text = number
    }

    override fun getItemCount(): Int = listDoctor.size
}