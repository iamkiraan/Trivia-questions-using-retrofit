package com.example.footballfixture

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    private lateinit var tv8: TextView
    private lateinit var tv9: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tv8 = findViewById(R.id.textView8)
        tv9 = findViewById(R.id.textView9)

        val incorrect = intent.getIntExtra("Incorrect", 0)
        val correct = intent.getIntExtra("Correct", 0)

        val totalQuestions = correct + incorrect
        val percentage: Double = if (totalQuestions > 0) {
            (correct.toDouble() / totalQuestions) * 100
        } else {
            0.0
        }

        tv8.text = "${String.format("%.2f", percentage)}%"
        if(percentage>=80){
            tv9.setText("congratulation!")
            tv9.setTextColor(Color.GREEN)
        }
        else{
            tv9.setText("oops! try agian")
            tv9.setTextColor(Color.RED)
        }

    }
}
