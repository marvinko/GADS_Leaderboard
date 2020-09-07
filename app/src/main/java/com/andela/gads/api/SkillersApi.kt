package com.andela.gads.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SkillersApi {
    @GET("skilliq")
    suspend fun getSkillers(): Response<List<Skiller>>

    companion object{
        operator fun invoke() : SkillersApi {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://gadsapi.herokuapp.com/api/")
                    .build()
                    .create(SkillersApi::class.java)
        }
    }
}