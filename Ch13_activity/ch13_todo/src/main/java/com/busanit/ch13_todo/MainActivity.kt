package com.busanit.ch13_todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch13_todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  var datas: MutableList<String>? = null
  lateinit var adapter: MyAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val toolbar = binding.toolbar
    setSupportActionBar(toolbar)

    // 버튼 누르면 AddActivity로 가서 입력값을 받아 옴
    val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
      ActivityResultContracts.StartActivityForResult()
    ) {
      it.data!!.getStringExtra("result")?.let {
        datas?.add(it)
        adapter.notifyDataSetChanged() // 어댑터에 데이터 변경 알림
      }
    }
    val mainFab = binding.mainFab
    mainFab.setOnClickListener {
      val intent = Intent(this, AddActivity::class.java)
      requestLauncher.launch(intent)
    }
    // 저장되어 있는 리스크가 있으면 복원, 없으면 초기화
    datas = savedInstanceState?.let {
      it.getStringArrayList("datas")?.toMutableList()// StringArrayList를 MutableList로 변환
    } ?: let {
      mutableListOf<String>()
    }
    // 리사이클러뷰에 layoutmanager, adapter 연결
    val layoutManager = LinearLayoutManager(this)
    val recyclerView = binding.mainRecyclerView
    recyclerView.layoutManager = layoutManager
    adapter = MyAdapter(datas!!)
    recyclerView.adapter = adapter
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putStringArrayList("datas", ArrayList(datas))
  }
}