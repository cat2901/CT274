package com.example.ttquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ttquizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            if (binding.etName.text?.isEmpty() == true){
                Toast.makeText(this, "Please enter your name!", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra(Constants.USR_NAME, binding.etName.text.toString())
                startActivity(intent)
            }
        }


    }
}