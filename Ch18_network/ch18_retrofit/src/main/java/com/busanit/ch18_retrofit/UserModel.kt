package com.busanit.ch18_retrofit

import com.google.gson.annotations.SerializedName

data class UserModel(
  var id: String,
  var email: String,
  var first_name: String,
  var last_name: String,
  var avatar: String
)

data class ResponseData(
  @SerializedName("data")
  var userModel:UserModel
)