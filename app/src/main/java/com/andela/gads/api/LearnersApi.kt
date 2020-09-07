package com.andela.gads.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface LearnersApi {

    @GET("hours")
    suspend fun getLearners(): Response<List<Learner>>

    companion object{
        operator fun invoke() : LearnersApi {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://gadsapi.herokuapp.com/api/")
                    .build()
                    .create(LearnersApi::class.java)
        }
    }

}