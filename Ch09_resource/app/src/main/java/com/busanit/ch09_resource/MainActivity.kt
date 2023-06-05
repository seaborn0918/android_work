package com.busanit.ch09_resource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.busanit.ch09_resource.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val textview = binding.textView
    textview.text = getString(R.string.txt_data2)
    textview.setTextColor(ResourcesCompat.getColor(resources, R.color.txt_color, null))
    textview.setBackgroundResource(R.color.txt_bg_color)
    textview.setTextSize(resources.getDimension(R.dimen.txt_size))

    // 플랫폼 리소스 사용
    val imageView = binding.imageView
    imageView.setImageDrawable(ResourcesCompat.getDrawable(resources, android.R.drawable.alert_dark_frame, null))

    textview.text = getString(android.R.string.emptyPhoneNumber)
  }
}