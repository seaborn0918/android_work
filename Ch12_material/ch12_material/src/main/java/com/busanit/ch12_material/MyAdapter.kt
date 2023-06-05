package com.busanit.ch12_material

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch12_material.databinding.ItemRecyclerviewBinding

class MyViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return MyViewHolder(
      ItemRecyclerviewBinding.inflate(
        LayoutInflater.from(parent.context),
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
  }
}

// 3. Decoration 클래스 만들기
class MyDecoration(val context: Context) : RecyclerView.ItemDecoration() {
  // 개별 항목 꾸미기만 구현
  override fun getItemOffsets(
    outRect: Rect,
    view: View,
    parent: RecyclerView,
    state: RecyclerView.State
  ) {
    super.getItemOffsets(outRect, view, parent, state)
    val index = parent.getChildAdapterPosition(view) + 1
    if (index % 3 == 0) {
      outRect.set(10, 10, 10, 60)
    } else {
      outRect.set(10, 10, 10, 10)
    }
    view.setBackgroundColor(Color.LTGRAY)
    ViewCompat.setElevation(view, 20.0f)
  }
}