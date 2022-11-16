package com.github.ebrahimi16153.trivia.network

import com.github.ebrahimi16153.trivia.model.Question
import retrofit2.http.GET
import javax.inject.Singleton


@Singleton
interface QuestionApi {

    @GET("world.json")
    suspend fun getAllQuestion():Question

}