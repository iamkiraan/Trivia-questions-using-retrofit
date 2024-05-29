package com.example.footballfixture

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
//    private lateinit var tv8 : TextView
//    private lateinit var tv9 : TextView
//    private lateinit var sharedPref: SharedPreferences
//    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
//        editor = sharedPref.edit()
//        tv8  = findViewById(R.id.textView8)
//        tv9 = findViewById(R.id.textView9)
//        tv8.text = sharedPref.getInt("correct", 0).toString()
//        tv9.text = sharedPref.getInt("incorrect", 0).toString()

    }
}