package com.busanit.ch13_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.busanit.ch13_activity.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityDetailBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // 2. 인텐트 수신. 수신된 데이터 표시하기
    val textData = binding.textData
    val intent = intent
    val data1 = intent.getStringExtra("data1")
    var data2 = intent.getIntExtra("data2", 0)
    val str = "전달된 데이터 : $data1, $data2"
    textData.setText(str)

    // 3. 응답데이터 세팅
    data2 += 10
    val resultIntent = Intent(this, MainActivity::class.java)
    resultIntent.putExtra("result", "$data1, $data2 from DetailActivity" )
    setResult(RESULT_OK, resultIntent)
    finish() // 해당 액티비티 종료

  }
}