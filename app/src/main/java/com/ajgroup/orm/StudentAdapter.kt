package com.ajgroup.orm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(val listStudent: List<Student>): RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    private lateinit var binding: StudentAdapter
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
    }
}