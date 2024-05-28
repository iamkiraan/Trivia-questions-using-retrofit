package com.example.footballfixture

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var ques: TextView
    private lateinit var tv2: TextView
    private lateinit var tv3: TextView
    private lateinit var tv4: TextView
    private lateinit var next: Button
    private lateinit var exit: Button
    private lateinit var tv5: TextView
    private lateinit var tv6: TextView
    private lateinit var tv7: TextView
    private lateinit var TitlE: TextView
    private lateinit var Timer : TextView
    private var i = 0
    private var Correct = 0
    private var Incorrect = 0
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initializeViews()
        loadQuestion()

        next.setOnClickListener {
            loadQuestion()
        }

    }

    private fun initializeViews() {
        tv2 = findViewById(R.id.textView2)
        tv3 = findViewById(R.id.textView3)
        ques = findViewById(R.id.textView)
        tv5 = findViewById(R.id.textView5)
        tv4 = findViewById(R.id.textView4)
        tv6 = findViewById(R.id.textView6)
        next = findViewById(R.id.button)
        tv7 = findViewById(R.id.textView7)
        TitlE = findViewById(R.id.title)
        Timer = findViewById(R.id.timer)
    }

    private fun loadQuestion() {
        cancelTimer()
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retro = retrofitBuilder.create(ApiInterface::class.java)
        val data = retro.getData()
        data.enqueue(object : Callback<quizquestion?> {
            override fun onResponse(call: Call<quizquestion?>, response: Response<quizquestion?>) {
                val responseBody = response.body()
                val results = responseBody?.results ?: return

                if (results.isNotEmpty()) {
                    val question = results[0]
                    ques.text = question.question
                    TitlE.text = question.category

                    val answers = mutableListOf(
                        question.correct_answer,
                        question.incorrect_answers[0],
                        question.incorrect_answers[1],
                        question.incorrect_answers[2]
                    ).shuffled()

                    tv2.text = answers[0]
                    tv3.text = answers[1]
                    tv4.text = answers[2]
                    tv5.text = answers[3]

                    tv2.setTextColor(Color.BLACK)
                    tv3.setTextColor(Color.BLACK)
                    tv4.setTextColor(Color.BLACK)
                    tv5.setTextColor(Color.BLACK)

                    tv2.setOnClickListener { handleAnswerClick(tv2, question.correct_answer) }
                    tv3.setOnClickListener { handleAnswerClick(tv3, question.correct_answer) }
                    tv4.setOnClickListener { handleAnswerClick(tv4, question.correct_answer) }
                    tv5.setOnClickListener { handleAnswerClick(tv5, question.correct_answer) }

                    startTimer()
                }
            }

            override fun onFailure(call: Call<quizquestion?>, t: Throwable) {
                Log.d("failure", "message: ${t.message}")
            }
        })
    }

    private fun handleAnswerClick(textView: TextView, correctAnswer: String) {
        cancelTimer()
        if (textView.text == correctAnswer) {
            textView.setTextColor(Color.GREEN)
            Correct++
            tv6.text = Correct.toString()
        } else {
            textView.setTextColor(Color.RED)
            Incorrect++
            tv7.text = Incorrect.toString()
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Timer.text = "Timer remaining: " + millisUntilFinished / 1000
                Timer.setTextColor(Color.RED)
            }

            override fun onFinish() {
                loadQuestion()
            }
        }.start()
    }

    private fun cancelTimer() {
        countDownTimer?.cancel()
    }
}
