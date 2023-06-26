package com.busanit.androidchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.busanit.androidchallenge.databinding.ActivityMainBinding
import com.busanit.androidchallenge.model.WeatherModel
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

  lateinit var adapter: MyFragmentPagerAdapter

  class MyFragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    val fragments: List<Fragment>

    init {
      fragments = listOf(OneFragment(), TwoFragment())
    }

    override fun getItemCount(): Int {
      return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
      return fragments[position]
    }
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // 툴바
    val toolbar = binding.toolbar
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    // 현재 시간 날씨 정보
    fun getWeatherInfo() {
      val now: Long = System.currentTimeMillis()
      val date = Date(now)
      val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
      val hourFormat = SimpleDateFormat("kk", Locale.getDefault())
      val minuteFormat = SimpleDateFormat("mm", Locale.getDefault())
      val dateTimeFormat = SimpleDateFormat("yyyy년 MM월 dd일 kk:mm", Locale.getDefault())


      val today = dateFormat.format(date)
      var onTime = (hourFormat.format(date).toInt() - 1).toString() + "00"
      // var onTime = hourFormat.format(date)
      val minute = minuteFormat.format(date).toInt()
      val dateTime = dateTimeFormat.format(date)

      binding.today.text = dateTime

//      if (minute < 40) {
//        onTime + "00"
//      } else {
//        onTime = (onTime.toInt() - 1).toString() + "00"
//      }

      val call: Call<WeatherModel>? =
        WeatherApi.networkService.getWeather("JSON", 10, 1, today, onTime, "63", "89")
      call?.enqueue(object : Callback<WeatherModel> {
        override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {

          val resultCode = response.body()?.response?.header?.resultCode
//          if (resultCode == 1) {
//            Toast.makeText(this, "제공기관 서비스 제공 상태가 원할하지 않습니다.", Toast.LENGTH_SHORT).show()
//          }

          if (response.isSuccessful) {
            Log.d("myLog", "${response.body()}")
            val weatherInfo = response.body()?.response?.body?.items?.item?.forEach { data ->
              when (data.category) {
                "T1H" -> {
                  binding.weatherText.text = data.obsrValue + "°"
                }

                "RN1" -> {
                  binding.precipitation.text = data.obsrValue + "mm"
                }

                "REH" -> {
                  binding.humidity.text = data.obsrValue + "%"
                }
              }
            }
          }
        }

        override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
          Log.d("myLog", "통신 실패 : ${t.message}")
        }
      })
    }
    getWeatherInfo()

    // 새로고침 아이콘 클릭 시
    binding.getQuery.setOnClickListener {
      getWeatherInfo()
    }

    val tabs = binding.tabs
    val viewPager = binding.viewPager
    val tabTextList = listOf("to-do", "journal")

    adapter = MyFragmentPagerAdapter(this)
    viewPager.adapter = adapter
    TabLayoutMediator(tabs, viewPager) { tab, position ->
      tab.text = tabTextList[position]
    }.attach()
  }

//  override fun onBackPressed() {
//    var time: Long = 0
//    if (System.currentTimeMillis() - time >= 2000) {
//      time = System.currentTimeMillis()
//      val toast = Toast.makeText(this, "종료하려면 한 번 더 누르세요", Toast.LENGTH_SHORT)
//      toast.show()
//    } else {
//      finish()
//    }
//  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        finish()
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_search, menu)
    return super.onCreateOptionsMenu(menu)
  }
}