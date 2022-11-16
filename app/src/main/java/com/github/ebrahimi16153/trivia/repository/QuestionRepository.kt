package com.github.ebrahimi16153.trivia.repository

import com.github.ebrahimi16153.trivia.util.data.DataOrException
import com.github.ebrahimi16153.trivia.model.QuestionItem
import com.github.ebrahimi16153.trivia.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api:QuestionApi) {

    private val listOfQuestion = DataOrException<ArrayList<QuestionItem>,Boolean,Exception>()

}