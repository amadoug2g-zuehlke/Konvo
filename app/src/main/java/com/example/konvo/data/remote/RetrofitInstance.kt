package com.example.konvo.data.remote

import com.example.konvo.utils.Constants.AUTH_KEY
import com.example.konvo.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer $AUTH_KEY")
                .addHeader("OpenAI-Beta", "assistants=v2")
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val openAIService: OpenAIApi by lazy {
        retrofit.create(OpenAIApi::class.java)
    }
}