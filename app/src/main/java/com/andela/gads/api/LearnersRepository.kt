package com.andela.gads.api

class LearnersRepository(private val api:LearnersApi ): SafeApiRequest() {

    suspend fun getLearners() = apiRequest { api.getLearners() }
}