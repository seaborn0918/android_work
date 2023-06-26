package com.busanit.androidchallenge

import android.app.Application
import android.app.DownloadManager.Query
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApi : Application() {
  companion object {
    const val API_KEY =
      "xzRt%2BRb3XI2CQmzFl9H1S%2Fv0wxvDePBIFCskDxrSuPnkyc60EgtNtTgoJhUC9peAJbIpjT6JDNC1jhUpAxcTUA%3D%3D"
    val BASE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/"

    val gson : Gson = GsonBuilder().setLenient().create()

    val retrofit: Retrofit = Retrofit.Builder()
      .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()
    var networkService: INetworkService = retrofit.create(INetworkService::class.java)
  }
}