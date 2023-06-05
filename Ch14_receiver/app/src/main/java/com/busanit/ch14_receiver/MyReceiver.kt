package com.busanit.ch14_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Sms
import android.telephony.SmsMessage
import java.text.SimpleDateFormat
import java.util.Date

class MyReceiver : BroadcastReceiver() {

  // 화면에 표시할 날짜 형식 정리
  val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  override fun onReceive(context: Context, intent: Intent) {
    // 문자 수신
    if (intent.action == "android.provider.Telephony.SMS_RECEIVED") {
      val bundle = intent.extras
      val message = parseMessaged(bundle)

      if (message.size > 0) {
        val sender = message[0]?.originatingAddress.toString()
        val contents = message[0]?.messageBody.toString()
        val receivedDate = Date(message[0]?.timestampMillis!!.toLong())
        sendToActivity(context, sender, contents, receivedDate)
      }

    }
  }

  private fun sendToActivity(
    context: Context,
    sender: String,
    contents: String,
    receivedDate: Date
  ) {
    val intent = Intent(context, SmsActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
    intent.putExtra("sender", sender)
    intent.putExtra("contents", contents)
    intent.putExtra("receivedDate", format.format(receivedDate))
    context?.startActivity(intent)
  }

  private fun parseMessaged(bundle: Bundle?): Array<SmsMessage?> {
    val objs = bundle?.get("pdus") as Array<Any>
    val messages = arrayOfNulls<SmsMessage>(objs.size) // 당장 데이터를 넣지는 않음
    for (i in 0 until messages.size) {
      val format = bundle?.getString("format")
      messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray, format)
    }
    return messages
  }
}