package com.busanit.ch13_coruoutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.busanit.ch13_coruoutine.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val btnGo = binding.btnGo
    val resultView = binding.resultView
//    btnGo.setOnClickListener {//EditText 터치하면 ANR 발생
//      var sum = 0L
//      val time = measureTimeMillis { // {} 실행하는데 소요된 시간 리턴
//        for (i in 1.. 6_000_000_000)
//          sum += i
//      }
//      Log.d("myLog", "time : $time")
//      resultView.text = "sum : $sum"
//    }

    // 대안1. 스레드-핸들러 구조
//    btnGo.setOnClickListener {
//      val handler = object : Handler() {
//        override fun handleMessage(msg: Message) { // 메인 스레드에서 UI 변경
//          super.handleMessage(msg)
//          resultView.text = "sum : ${msg.arg1}"
//        }
//      }
//      thread { // 별도 스레드 생성
//        var sum = 0L
//        var time = measureTimeMillis {
//          for (i in 1..6_000_000_000){
//            sum += i
//          }
//          val message = Message()
//          message.arg1 = sum.toInt()
//          handler.sendMessage(message)
//        }
//        Log.d("myLog", "time : $time")
//      }
//    }

    // 대안 2. 코루틴 활용 (build.gradle에 종속성 추가)
    btnGo.setOnClickListener {
      val channel = Channel<Int>() // 코루틴에서 데이터 송수신
      val backgroundScope = CoroutineScope(Dispatchers.Default + Job()) // 백그라운드에서 오래 걸리는 작업
      backgroundScope.launch {
        var sum = 0L
        var time = measureTimeMillis {
          for (i in 1..2_000_000_000){
            sum += i
          }
        }
        Log.d("myLog", "time = : $time")
        channel.send(sum.toInt())
      }
    val mainScope = GlobalScope.launch(Dispatchers.Main) {//메인 스레드에서 동작
      channel.consumeEach {
        resultView.text = "sum : $it"
      }
    }
    }
  }
}