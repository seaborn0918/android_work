package com.busanit.ch18_retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface INetworkService { // 사용할 함수 선언만 규정
  // getQuery
  @GET("api/users")
  fun doGetUerList(@Query("page") page: String): Call<UserListModel>
  // 실제 url 주소 : https://reqres/in/api/user?page=넘어온 page 값

  // getPath
  @GET("api/users/{id}")
  fun doGetUser(@Path("id") userId: Int): Call<ResponseData>
  // 실제 url 주소 : https://reqres/in/api/users/넘어온 id 값
}