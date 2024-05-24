package com.example.footballfixture

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("api.php?amount=10&category=9&difficulty=easy&type=multiple")
    fun getData() : Call<quizquestion>
}