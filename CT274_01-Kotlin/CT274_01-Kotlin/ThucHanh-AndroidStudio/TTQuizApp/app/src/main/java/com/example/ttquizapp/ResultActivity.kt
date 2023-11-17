package com.example.ttquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ttquizapp.databinding.ActivityMainBinding
import com.example.ttquizapp.databinding.ActivityQuizBinding
import com.example.ttquizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultBinding

    private var userName:String? = null
    private var correctAnswers:Int = 0
    private var totalQuestion:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)

        setContentView(binding.root)

        userName = intent.getStringExtra(Constants.USR_NAME)
//        binding.tvUserName.text = userName
        binding.tvUserName.text = userName

        correctAnswers = intent.getIntExtra(Constants.CORRECT_ANS, 0)
        totalQuestion = intent.getIntExtra(Constants.TOTAL_QUESTION, 0)
        binding.tvScore.text = "Your score is $correctAnswers out of $totalQuestion"

    }
}