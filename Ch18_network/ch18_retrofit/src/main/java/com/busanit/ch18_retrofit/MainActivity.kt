package com.busanit.ch18_retrofit

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.busanit.ch18_retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

// 4. Retrofit 객체를 만들어서 제공할 클래스 구현
// 컴패니언 클래스 : 이후 클래스명으로 함수에 접근

class MyApplication : Application() {
  companion object {
    var retrofit: Retrofit
    var networkService: INetworkService

    init {
      retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
      networkService = retrofit.create(INetworkService::class.java)
    }
  }
}

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.getQuery.setOnClickListener {
      val userListCall = MyApplication.networkService.doGetUerList("1")
      userListCall.enqueue(object : Callback<UserListModel> {
        // 통신 성공 시 호출
        override fun onResponse(call: Call<UserListModel>, response: Response<UserListModel>) {
          val userList = response.body()
          Log.d("myLog", "userList : $userList")
        }

        // 통신 실패 시 호출
        override fun onFailure(call: Call<UserListModel>, t: Throwable) {
          Log.d("myLog", "통신 실패")
        }
      })
    }

    binding.getPath.setOnClickListener {
      var userModel = MyApplication.networkService.doGetUser(1)
      userModel.enqueue(object : Callback<ResponseData> {
        override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
          val user = response.body()
          Log.d("myLog", "user : $user")
        }

        override fun onFailure(call: Call<ResponseData>, t: Throwable) {
          Log.d("myLog", "통신 실패 : ${t.message}")
        }
      })
    }

    // glide 사용하기
    binding.glideTest.setOnClickListener {
      Glide.with(this)
        .load("https://reqres.in/img/faces/1-image.jpg") // 주소
        .override(500,500) // 크기 조절
        .placeholder(R.mipmap.ic_launcher_round) // 이미지 로딩 시 최초에 보여줄 이미지
        .error(R.drawable.todo) // 에러 발생 시 보여줄 이미지
        .into(binding.glideImage) // 이미지 출력할 뷰 전달
    }
  }
}