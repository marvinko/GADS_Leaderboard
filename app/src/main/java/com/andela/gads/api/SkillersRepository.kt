package com.andela.gads.api

class SkillersRepository(private val api:SkillersApi ): SafeApiRequest() {

    suspend fun getSkillers() = apiRequest { api.getSkillers() }
}