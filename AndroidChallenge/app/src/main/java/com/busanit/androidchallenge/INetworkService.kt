package com.busanit.androidchallenge

import com.busanit.androidchallenge.WeatherApi.Companion.API_KEY
import com.busanit.androidchallenge.model.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface INetworkService {
  @GET("getUltraSrtNcst?serviceKey=$API_KEY")
  fun getWeather(
    @Query("dataType") dataType: String,
    @Query("numOfRows") numOfRows : Int,
    @Query("pageNo") pageNo : Int,
    @Query("base_date") baseDate : String,
    @Query("base_time") baseTime : String,
    @Query("nx") nx : String,
    @Query("ny") ny : String
  ) : Call<WeatherModel>
}