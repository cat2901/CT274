package com.example.ttquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ttquizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuizBinding

    private var userName:String? = null
    private var questionList:ArrayList<Question>? = null
    private var currentPosition:Int = 1
    private var selectedOption:Int = 0

    private var correctAnswers:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userName = intent.getStringExtra(Constants.USR_NAME)
        questionList = Constants.getQuestions()

        binding.progressBar.max = questionList?.size!!
        setQuestion()

        binding.tvOption1.setOnClickListener { setSelectedOption(binding.tvOption1, 1) }
        binding.tvOption2.setOnClickListener { setSelectedOption(binding.tvOption2, 2) }
        binding.tvOption3.setOnClickListener { setSelectedOption(binding.tvOption3, 3) }
        binding.tvOption4.setOnClickListener { setSelectedOption(binding.tvOption4, 4) }

        binding.btnSubmit.setOnClickListener { onSubmit() }

    }

    private fun setSelectedOption(view: TextView, num: Int){
        defaultOptionView()
        selectedOption = num
        view.setTextColor(Color.parseColor("#363A43"))
        view.setTypeface(view.typeface, Typeface.BOLD)
        view.setBackgroundResource(R.drawable.tv_border_select)
    }

    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        options.add(binding.tvOption1)
        options.add(binding.tvOption2)
        options.add(binding.tvOption3)
        options.add(binding.tvOption4)

        for (option in options){
            option.typeface = Typeface.DEFAULT
            option.setBackgroundResource(R.drawable.tv_border_default)
            option.setTextColor(Color.parseColor("#7a8089"))
        }


    }

    private fun setQuestion() {
        defaultOptionView()
        var  question:Question = questionList!![currentPosition-1]
        binding.progressBar.progress = currentPosition
        binding.tvProgressBar.text = "$currentPosition/${binding.progressBar.max}"
        binding.tvQuestion.text = question.question
        binding.tvOption1.text = question.opt1
        binding.tvOption2.text = question.opt2
        binding.tvOption3.text = question.opt3
        binding.tvOption4.text = question.opt4
        binding.ivImage.setImageResource(question.image)

        if (currentPosition == questionList!!.size){
            binding.btnSubmit.text = "FINISH"
        }
        else{
            binding.btnSubmit.text = "SUBMIT"
        }
    }

    private fun onSubmit() {
        if (selectedOption==0){
            currentPosition++
            if (currentPosition<= questionList!!.size){
                setQuestion()
            }
            else{
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(Constants.CORRECT_ANS, correctAnswers)
                intent.putExtra(Constants.TOTAL_QUESTION, questionList!!.size)
                startActivity(intent)
            }
        }
        else{
            val question = questionList?.get(currentPosition-1)
            if(correctAnswers!=selectedOption){
                answerView(selectedOption, R.drawable.tv_border_incorrect)
            }
            else{
                correctAnswers++;
            }
            answerView(question!!.correctAnswer, R.drawable.tv_border_correct)

            if(currentPosition==questionList!!.size){
                binding.btnSubmit.text = "FINISH"
            }
            else{
                binding.btnSubmit.text = "GO TO THE NEXT QUESTION"
            }
            selectedOption = 0
        }
    }

    private fun answerView(selected: Int, bg: Int) {
        when(selected){
            1 -> {binding.tvOption1.setBackgroundResource(bg)}
            2 -> {binding.tvOption2.setBackgroundResource(bg)}
            3 -> {binding.tvOption3.setBackgroundResource(bg)}
            4 -> {binding.tvOption4.setBackgroundResource(bg)}
        }
    }

}