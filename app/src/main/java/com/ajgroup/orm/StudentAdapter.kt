package com.ajgroup.orm

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ajgroup.orm.databinding.ActivityAddBinding
import com.ajgroup.orm.databinding.ActivityEditBinding
import com.ajgroup.orm.databinding.StudentItemBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class StudentAdapter(val listStudent: List<Student>): RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    class ViewHolder(val binding:StudentItemBinding,val binding1: ActivityAddBinding,val binding2: ActivityEditBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StudentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val binding1 = ActivityAddBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val binding2 = ActivityEditBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding, binding1, binding2)
    }

    override fun getItemCount(): Int {
        return listStudent.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvId.text = listStudent[position].id.toString()
        holder.binding.tvNama.text = listStudent[position].nama
        holder.binding.tvEmail.text = listStudent[position].email

        holder.binding.ivEdit.setOnClickListener { 
            val intentKeEditActivity = Intent(it.context,
            EditActivity::class.java)
            
            intentKeEditActivity.putExtra("student",listStudent[position])
            it.context.startActivity(intentKeEditActivity)
        }
        
        holder.binding.ivDelete.setOnClickListener { 
            AlertDialog.Builder(it.context).setPositiveButton("Ya") {
                p0, p1 ->
                val mDb = StudentDatabase.getInstance(holder.binding.root.context)
                
                GlobalScope.async { 
                    val result = mDb?.studentDao()?.deleteStudent(listStudent[position])
                    (holder.binding.root.context as MainActivity).runOnUiThread { 
                        if (result!=0){
                            Toast.makeText(it.context, "DAta ${listStudent[position].nama} berhasil dihapus", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(it.context, "Data ${listStudent[position].nama} Gagal dihapus", Toast.LENGTH_SHORT).show()
                        }
                    }
                    (holder.binding.root.context as MainActivity).fetchData()
                }
            }.setNegativeButton("Tidak"){
                p0,p1 ->
                p0.dismiss()
            }
                .setMessage("Apakah Anda Yakin Ingin menghapus data ${listStudent[position].nama}").setTitle("Konfrimasi Hapus").create().show()
        }

    }
}