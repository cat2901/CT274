package com.example.b2012045_myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlin.math.log

class QuizQuestionActivity : AppCompatActivity() {
    private var userName: String? = null
    private val questionList: ArrayList<Question> = Constants.getQuestions()
    private var questionIndex = 0;
    private var player_answer = -1;
    private var isAnswerChecked = false;
    private var totalScore = 0;

    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var btnSubmit: Button? = null
    private var tvOptions: ArrayList<TextView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        userName = intent.getStringExtra("username")
        updateQuestion()
        getComponent()
        btnSubmit?.setOnClickListener {
            if (!isAnswerChecked) {
                handleAnswerSubmission()
            } else {
                goToNextQuestionOrFinishQuiz()
            }
        }
        setupOptionClickListeners(isAnswerChecked)
    }
    private fun getComponent(){
        tvQuestion = findViewById(R.id.tvQuestion)
        ivImage= findViewById(R.id.ivImage)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvOptions = arrayListOf(
            findViewById(R.id.tvOptionOne),
            findViewById(R.id.tvOptionTwo),
            findViewById(R.id.tvOptionThree),
            findViewById(R.id.tvOptionFour),
        )
    }
    // Hàm xử lý việc nộp câu trả lời.
    private fun handleAnswerSubmission() {
        if (player_answer == -1) {
            // Người dùng chưa chọn câu trả lời.
            showToast("Hãy chọn đáp án của bạn")
        } else {
            val currentQuestion = questionList[questionIndex]
            handleAnswerEvaluation(currentQuestion)
            isAnswerChecked = true
            btnSubmit?.text = if (questionIndex == questionList.size - 1) "FINISH" else "GO TO NEXT QUESTION"
            player_answer = -1
        }
    }
    // Hàm xử lý việc đánh giá câu trả lời của người dùng.
    private fun handleAnswerEvaluation(currentQuestion: Question) {
        if (player_answer == currentQuestion.correctAnswer) {
            // Đáp án đúng.
            answerView(tvOptions!![player_answer], R.drawable.correct_option_border_background)
            totalScore++
        } else {
            // Đáp án sai.
            answerView(tvOptions!![player_answer], R.drawable.wrong_option_border_background)
            answerView(tvOptions!![currentQuestion.correctAnswer], R.drawable.correct_option_border_background)
        }
    }
    // Hàm chuyển sang câu hỏi tiếp theo hoặc kết thúc bài thi nếu đã qua hết câu hỏi.
    private fun goToNextQuestionOrFinishQuiz() {
        if (questionIndex < questionList.size - 1) {
            // Chuyển sang câu hỏi tiếp theo.
            questionIndex++
            updateQuestion()
        } else {
            // Kết thúc bài thi và hiển thị kết quả.
            goToResultActivity()
        }
        isAnswerChecked = false
    }
    // Hàm hiển thị thông báo Toast.
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    // Hàm chuyển sang màn hình kết quả.
    private fun goToResultActivity() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("username", userName)
        intent.putExtra("total", questionList.size)
        intent.putExtra("score", totalScore)
        startActivity(intent)
        finish()
    }
    private fun setupOptionClickListeners(isAnswerChecked: Boolean) {
        if (!isAnswerChecked) {
            tvOptions?.forEachIndexed { index, option ->
                option.setOnClickListener {
                    selectedView(option, index)
                }
            }
        }
    }
    private fun updateQuestion() {
        defaultView()
        tvQuestion?.text = questionList[questionIndex].question
        ivImage?.setImageResource(questionList[questionIndex].image)
        progressBar?.progress = questionIndex + 1
        tvProgress?.text = "${questionIndex + 1}/${questionList.size}"
        val currentQuestion = questionList[questionIndex]
        val options = listOf(
            currentQuestion.optionOne,
            currentQuestion.optionTwo,
            currentQuestion.optionThree,
            currentQuestion.optionFour
        )
        for (optionIndex in 0 until options.size) {
            tvOptions!![optionIndex].text = options[optionIndex]
        }
        btnSubmit?.text = if (questionIndex == questionList.size - 1) "FINISH" else "SUBMIT"
    }
    private fun defaultView() {
        for (alternativeTv in tvOptions!!) {
            alternativeTv.typeface = Typeface.DEFAULT
            alternativeTv.setTextColor(Color.parseColor("#7A8089"))
            alternativeTv.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_background
            )
        }
    }
    private fun selectedView(option: TextView, index: Int) {
        defaultView()
        player_answer = index
        option.setTextColor(
            Color.parseColor("#363A43")
        )
        option.setTypeface(option.typeface, Typeface.BOLD)
        option.background = ContextCompat.getDrawable(
            this,
            R.drawable.select_option_border_background
        )
    }
    private fun answerView(view: TextView, drawableId: Int) {
        view.background = ContextCompat.getDrawable(
            this,
            drawableId
        )
        tvOptions!![player_answer].setTextColor(
            Color.parseColor("#FFFFFF")
        )
    }
}