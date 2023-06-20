package com.busanit.ch19_location

import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.busanit.ch19_location.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    val manager = getSystemService(LOCATION_SERVICE) as LocationManager
    // 기기 위치 제공자 확인
//    binding.btnPro.setOnClickListener {
//      var result = "All Provides : "
//      val providers = manager.allProviders
//      for (provider in providers) {
//        result += "$provider, "
//      }
//      binding.textPro.text = result
//    }

    // 사용 가능한 위치 제공자 확인
    binding.btnPro.setOnClickListener {
      var result = "Enabled Provides : "
      val providers = manager.getProviders(true)
      for (provider in providers) {
        result += "$provider, "
      }
      binding.textPro.text = result
    }

    // 위치 정보 가져오기
    binding.btnLoc.setOnClickListener {
      if (ContextCompat.checkSelfPermission(
          this,
          android.Manifest.permission.ACCESS_FINE_LOCATION
        ) === PackageManager.PERMISSION_GRANTED
      ) {
        val location: Location? = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        location?.let {
          val latitude = it.latitude
          val longitude = it.longitude
          val accuracy = it.accuracy
          val time = it.time
          binding.textLoc.text = "위도: $latitude, 경도 : $longitude"
        }
      }
    }

    // 위치 변동 감지. listener
    val listener: LocationListener = object : LocationListener {
      override fun onLocationChanged(location: Location) {
        binding.textLoc.text = "위도 : ${location.latitude} 경도 : ${location.longitude}"
      }
    }
    manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10_000L, 10f, listener)
  }
}