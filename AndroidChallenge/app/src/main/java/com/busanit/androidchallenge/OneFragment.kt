package com.busanit.androidchallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.androidchallenge.databinding.FragmentOneBinding

class OneFragment : Fragment() {
  lateinit var binding: FragmentOneBinding
  lateinit var adapter: MyAdapter
  var datas: MutableList<String>? = null
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentOneBinding.inflate(inflater, container, false)

    val requestLauncher = registerForActivityResult(
      ActivityResultContracts.StartActivityForResult()
    ) {
      it.data?.getStringExtra("result")?.let {
        datas?.add(it)
        adapter.notifyDataSetChanged()
      }
    }

    val todoFab = binding.todoFab
    todoFab.setOnClickListener {
      val intent = Intent(context, AddActivity::class.java)
      requestLauncher.launch(intent)
    }
    return binding.root
  }

  override fun onResume() {
    super.onResume()

    datas = mutableListOf()
    val db = DBHelper(activity as Context).readableDatabase
    val cursor = db.rawQuery("select * from TODO", null)
    cursor.run {
      while (moveToNext()) {
        datas?.add(cursor.getString(1))
      }
    }
    db.close()
    val todoRecyclerview = binding.todoRecyclerView
    todoRecyclerview.layoutManager = LinearLayoutManager(activity)
    adapter = MyAdapter(datas!!)
    todoRecyclerview.adapter = adapter
    todoRecyclerview.addItemDecoration(DividerItemDecoration(activity as Context, LinearLayoutManager.VERTICAL))
  }

}