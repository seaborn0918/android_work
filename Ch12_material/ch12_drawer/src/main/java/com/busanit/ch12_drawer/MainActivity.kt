package com.busanit.ch12_drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.busanit.ch12_drawer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val mainDrawer = binding.mainDrawerView
    mainDrawer.setNavigationItemSelectedListener {
      Log.d("myLog", "navigation item click... ${it.title}")
      true
    }

    // 플로팅 액션 버튼
    val efaBtn = binding.efaBtn
    efaBtn.setOnClickListener{
      when(efaBtn.isExtended){
        true -> efaBtn.shrink()
        false -> efaBtn.extend()
      }
    }
  }
}