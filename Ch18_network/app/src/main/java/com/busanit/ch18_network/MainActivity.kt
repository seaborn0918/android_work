package com.busanit.ch18_network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.busanit.ch18_network.databinding.ActivityMainBinding
import org.json.JSONObject
import java.lang.StringBuilder
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
  lateinit var queue: RequestQueue
  lateinit var textView: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val button = binding.button
    textView = binding.textView
    button.setOnClickListener {
      makeRequest()
    }
    queue = Volley.newRequestQueue(this)
  }

  private fun makeRequest() {
    // targetDt 구하기(어제 날짜)
    val cal = Calendar.getInstance()
    cal.add(Calendar.DATE, -1)
    val df = SimpleDateFormat("yyyyMMdd")
    textView.append("Daily Box Office : ${df.format(cal.time)}\n\n")

    // 1. StringRequest로 xml 문서 보여주기
//    val str =
//      StringBuilder("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml")
//    str.append("?" + URLEncoder.encode("key", "UTF-8") + "=ca4789feb3f4a0520393d8d605df67a9")
//    str.append("&" + URLEncoder.encode("targetDt", "UTF-8") + "=${df.format(cal.time)}")
//    Log.d("myLog", "Url : $str")

//    val stringRequest = object : StringRequest(
//      Request.Method.GET,
//      str.toString(),
//      Response.Listener<String> {// 성공했을 때
//        textView.append(it)
//      },
//      Response.ErrorListener {error ->  // 실패했을 때
//        Log.d("myLog", "error : ${error.message}")
//      }) {
//      override fun getParams(): MutableMap<String, String>? {
//        return super.getParams()
//      }
//    }
//    stringRequest.setShouldCache(false)
//    queue.add(stringRequest)

    // 2. JsonObjectRequest로 데이터 파싱하기
    val str =
      StringBuilder("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    str.append("?" + URLEncoder.encode("key", "UTF-8") + "=ca4789feb3f4a0520393d8d605df67a9")
    str.append("&" + URLEncoder.encode("targetDt", "UTF-8") + "=${df.format(cal.time)}")
    Log.d("myLog", "Url : $str")

    val jsonRequest = object : JsonObjectRequest(
      Request.Method.GET,
      str.toString(),
      null,
      Response.Listener<JSONObject> {response ->
        val jsonObject = response.getJSONObject("boxOfficeResult")
        val jsonArray = jsonObject.getJSONArray("dailyBoxOfficeList")
        for (i in 0 until jsonArray.length()){
          val movie = jsonArray.getJSONObject(i)
          val rank = movie.getString("rank")
          val title = movie.getString("movieNm")
          val audiAcc = movie.getString("audiAcc")
          textView.append(rank + ". " + title + ", " + audiAcc + "명 관람\n")
        }
      },
      Response.ErrorListener {error ->
        Log.d("myLog", "통신 실패 : $error")
      }
    ) {
      override fun getHeaders(): MutableMap<String, String> {
        return super.getHeaders()
      }
    }
    queue.add(jsonRequest)
  }
}













