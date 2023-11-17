package com.example.b2012045_myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btnStart)
        val etName: EditText = findViewById(R.id.etName)

        btnStart.setOnClickListener {
            if(etName.text.isEmpty()){
                Toast.makeText(this, "Nhập tên của bạn", Toast.LENGTH_LONG).show()
            }
            else{
                val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra("username", etName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }


}