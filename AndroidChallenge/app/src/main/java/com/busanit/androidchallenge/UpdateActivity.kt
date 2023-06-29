package com.busanit.androidchallenge

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.busanit.androidchallenge.databinding.ActivityUpdateBinding

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
          val db = DBHelper(this).writableDatabase
          db.execSQL("update TODO set todo = ? where todo = ?", arrayOf(inputData, todo))
          db.close()

          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
          setResult(Activity.RESULT_OK, intent)
          finish()
          true
        }
      }
    }
    return super.onOptionsItemSelected(item)
  }
}