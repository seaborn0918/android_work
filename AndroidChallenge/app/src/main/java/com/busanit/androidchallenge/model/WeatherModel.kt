package com.busanit.androidchallenge.model

data class WeatherModel(
  val response: Response
) {

  data class Response(
    val header: Header,
    val body: Body
  )

  data class Header(
    val resultCode: Int,
    val resultMsg: String
  )

  data class Body(
    val dataType: String,
    val items: Items
  )

  data class Items(
    val item: List<Item>
  )

  data class Item(
    val baseDate: String,
    val baseTime: String,
    val category: String,
    val nx: Int,
    val ny: Int,
    val obsrValue: String
  )
}