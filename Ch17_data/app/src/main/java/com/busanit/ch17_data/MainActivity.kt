package com.busanit.ch17_data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.busanit.ch17_data.databinding.ActivityMainBinding
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val editName = binding.editName
    val editTel = binding.editTel
    val btnInsert = binding.btnInsert
    val btnSelect = binding.btnSelect
    val textView = binding.textView

    // 1. openOrCreateDatabase 활용
//    val db = openOrCreateDatabase("testdb", Context.MODE_PRIVATE, null)
//    db.execSQL(
//      "create table if not exists USER_TB(" +
//          "_id integer primary Key autoincrement," +
//          "name text not null," +
//          "phone text)"
//    )

    // 2. SQLiteOpenHelper 클래스 사용
    val db: SQLiteDatabase = DBHelper(this).writableDatabase
    // writableDatabase : 입력, 수정, 삭제
    // readableDatabase : 조회

    // 입력 1.
//    btnInsert.setOnClickListener {
//      db.execSQL(
//        "insert into USER_TB(name, phone) values (?,?)",
//        arrayOf<String>(editName.text.toString(), editTel.text.toString())
//      )
//
//      Toast.makeText(this, "입력되었습니다.", Toast.LENGTH_SHORT).show()
//      editName.text.clear()
//      editTel.text.clear()
//    }

    // 입력 2. insert() 함수 사용
    btnInsert.setOnClickListener {
      val values = ContentValues()
      values.put("name", editName.text.toString())
      values.put("phone", editTel.text.toString())
      db.insert("USER_TB", null, values)

      Toast.makeText(this, "입력되었습니다.", Toast.LENGTH_SHORT).show()
      editName.text.clear()
      editTel.text.clear()
    }

    // 조회 1.
//    btnSelect.setOnClickListener {
//      val cursor = db.rawQuery("select * from USER_TB", null)
//      val str = StringBuilder()
//      if (cursor != null) {
//        while (cursor.moveToNext()) {
//          val id = cursor.getInt(0)
//          val name = cursor.getString(1)
//          val phone = cursor.getString(2)
//          Log.d("myLog", "name : $name, phone : $phone")
//          str.append("$id. name : $name, phone : $phone\n")
//        }
//        textView.text = str
//      }
//    }

    // 조회 2. query() 함수 사용

    btnSelect.setOnClickListener {
      val cursor = db.query(
        "USER_TB", arrayOf("_id", "name", "phone"),
        null, null, null, null, null
      )
      val str = StringBuilder()
      if (cursor != null) {
        while (cursor.moveToNext()) {
          val id = cursor.getInt(0)
          val name = cursor.getString(1)
          val phone = cursor.getString(2)
          Log.d("myLog", "name : $name, phone : $phone")
          str.append("$id. name : $name, phone : $phone\n")
        }
        textView.text = str
      }
    }
  }
}