package com.busanit.ch18_news

import android.app.Application
import android.app.DownloadManager.Query

class MyApplication : Application() {
  companion object {
    val Query = "여행"
    val API_KEY = "4a88b5477ae342e8af2f4fd1e9475f50"
    val BASE_URL = "https://newsapi.org"
    val USER_AGENT =
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36(KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"
  }
}