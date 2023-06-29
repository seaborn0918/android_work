package com.busanit.androidchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
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
  lateinit var toggle: ActionBarDrawerToggle


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
    var drawer = binding.drawer
    toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    toggle.syncState()

    // 현재 시간 날씨 정보
    fun getWeatherInfo() {
      val now: Long = System.currentTimeMillis()
      val date = Date(now)
      val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
      val hourFormat = SimpleDateFormat("kk00", Locale.getDefault())
      var minuteFormat = SimpleDateFormat("mm", Locale.getDefault())
      val dateTimeFormat = SimpleDateFormat("yyyy년 MM월 dd일 kk:mm", Locale.getDefault())


      val today = dateFormat.format(date)
      var onTime = hourFormat.format(date)
      val minute = minuteFormat.format(date).toInt()
      val dateTime = dateTimeFormat.format(date)

      if (minute <= 20) {
        if (onTime.toInt() - 100 < 1000) {
          onTime = (onTime.toInt() - 100).toString()
          onTime = "0$onTime"
        } else {
          onTime = (onTime.toInt() - 100).toString()
        }
      }

      binding.today.text = dateTime

      // nx:98, ny:75 -> 부산진구 부전동
      val call: Call<WeatherModel>? =
        WeatherApi.networkService.getWeather("JSON", 10, 1, today, onTime, "98", "75")
      call?.enqueue(object : Callback<WeatherModel> {
        override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
          val resultCode = response.body()?.response?.header?.resultCode
          if (resultCode == 0) {
            Log.d("myLog", "${response.body()}")
            response.body()?.response?.body?.items?.item?.forEach { data ->
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
          } else {
            Toast.makeText(applicationContext, "기상청의 서비스 제공 상태가 원할하지 않습니다.", Toast.LENGTH_SHORT)
              .show()
            Log.d("myLog", "${response.body()}")
            Log.d("myLog", "기상청의 서비스 제공 상태가 원할하지 않습니다.")
          }
        }

        override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
          Log.d("myLog", "통신 실패 : ${t.message}")
          Toast.makeText(
            applicationContext,
            "통신 오류가 발생했습니다. 잠시 후 새로고침 버튼을 눌러주세요.",
            Toast.LENGTH_SHORT
          ).show()
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
    val tabTextList = listOf("to-do", "calendar")

    adapter = MyFragmentPagerAdapter(this)
    viewPager.adapter = adapter
    TabLayoutMediator(tabs, viewPager) { tab, position ->
      tab.text = tabTextList[position]
    }.attach()
  }

  private var time: Long = 0
  override fun onBackPressed() {
    if (System.currentTimeMillis() - time >= 2000) {
      time = System.currentTimeMillis()
      Toast.makeText(applicationContext, "한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show()
    } else {
      finish()
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if(toggle.onOptionsItemSelected(item)) {
      return true
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_search, menu)
    return super.onCreateOptionsMenu(menu)
  }
}