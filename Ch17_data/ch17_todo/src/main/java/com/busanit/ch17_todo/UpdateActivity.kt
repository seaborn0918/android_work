package com.busanit.ch17_todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.busanit.ch17_todo.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
  lateinit var binding: ActivityUpdateBinding
  lateinit var todo: String
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityUpdateBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setSupportActionBar(binding.updatebar)

    val intent = intent
    todo = intent.getStringExtra("todo").toString()
    binding.updateEditView.setText(todo)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_add, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.menu_add_save -> {
        val inputData = binding.updateEditView.text.toString()
        if (inputData.isBlank()) {
          Toast.makeText(this, "수정 항목을 확인해주세요", Toast.LENGTH_SHORT).show()
          false
        } else {
          // update 수정
          val db = DBHelper(this).writableDatabase
          db.execSQL("update TODO_TB set todo = ? where todo = ?", arrayOf(inputData, todo))
          db.close()
          val intent = Intent(this, MainActivity::class.java)
          // 플래그 설정
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
          startActivity(intent)
          finish()
          true
        }
      }
    }
    return super.onOptionsItemSelected(item)
  }

}