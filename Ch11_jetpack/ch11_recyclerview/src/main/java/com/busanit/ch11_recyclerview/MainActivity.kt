package com.busanit.ch11_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.busanit.ch11_recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // 3. 데이터 생성, 어댑터 레이아웃 매니저 생성, 출력하기
    val datas = mutableListOf<String>()
    for (i in 1..20) {
      datas.add("Item $i")
      // 5. StaggeredGridLayout
//      if (i == 3 || i == 4 || i == 8 || i == 13 || i == 15 || i == 19) {
//        datas.add("동해물과 백두산이 마르고 닳도록 $i")
//      } else {
//        datas.add("Item $i")
//      }
    }
    val recyclerView = binding.recyclerView
    val layoutManager = LinearLayoutManager(this)
    layoutManager.orientation = LinearLayoutManager.VERTICAL // 뷰홀더 추가하는 방향 HORIZONTAL - 가로 방향로 추가
    recyclerView.layoutManager = layoutManager

    val adapter = MyAdapter(datas)
    recyclerView.adapter = adapter
    // 뷰 홀더 사이 구분선 넣기
//    recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

    // 4. 데이터 추가, 제거
    val btnAdd = binding.btnAdd
    btnAdd.setOnClickListener {
      adapter.addItem()
    }
    binding.btnRemove.setOnClickListener {
      adapter.removeItem()
    }

    // 5. 레이아웃 매니저 변경
//    val layoutManager = GridLayoutManager(this, 2) // 2열로 배치
//    val layoutManager = GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false) // false 정순, true 역순
//    val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//    recyclerView.layoutManager = layoutManager

    // 6. 아이템 데코레이션 추가
    recyclerView.addItemDecoration(MyDecoration(this))
  }
}