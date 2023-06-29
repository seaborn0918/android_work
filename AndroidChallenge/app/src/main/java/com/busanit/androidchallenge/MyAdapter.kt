package com.busanit.androidchallenge

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.busanit.androidchallenge.databinding.ItemRecyclerviewBinding

class MyViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  lateinit var db: DBHelper
  lateinit var context: Context
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    context = parent.context
    db = DBHelper(context)
    return MyViewHolder(
      ItemRecyclerviewBinding.inflate(
        LayoutInflater.from(context),
        parent,
        false
      )
    )
  }

  override fun getItemCount(): Int {
    return datas.size
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val binding = (holder as MyViewHolder).binding
    binding.itemData.text = datas[position]
    // 삭제
    binding.imgDelete.setOnClickListener {
      val data = binding.itemData.text.toString()
      val delDb = db.writableDatabase
      delDb.execSQL("delete from TODO where todo = ?", arrayOf(data))
      delDb.close()

      datas.remove(datas[position])
      notifyDataSetChanged()
    }

    // 업데이트
    binding.imgUpdate.setOnClickListener {
      val intent = Intent(context, UpdateActivity::class.java)
      intent.putExtra("todo", binding.itemData.text.toString())
      context.startActivity(intent)
    }
  }
}