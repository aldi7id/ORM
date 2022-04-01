package com.ajgroup.orm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajgroup.orm.databinding.StudentItemBinding

class StudentAdapter(val listStudent: List<Student>): RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    class ViewHolder(val binding:StudentItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StudentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listStudent.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvId.text = listStudent[position].id.toString()
        holder.binding.tvName.text = listStudent[position].nama
        holder.binding.tvEmail.text = listStudent[position].email

    }
}