package com.busanit.ch12_material

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch12_material.databinding.FragmentOneBinding

class OneFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val binding = FragmentOneBinding.inflate(inflater, container, false)

    val recyclerView = binding.recyclerView
    val datas = mutableListOf<String>()
    for (i in 1..20) {
      datas.add("리사이클러 아이템$i")
    }

    // MyAdapter 만들기
    val adapter = MyAdapter(datas)
    val layoutManager = LinearLayoutManager(activity)
    recyclerView.layoutManager = layoutManager
    recyclerView.adapter = adapter
    recyclerView.addItemDecoration(MyDecoration(activity as Context))

    return binding.root
  }
}