package com.github.ebrahimi16153.trivia.repository

import android.util.Log
import com.github.ebrahimi16153.trivia.model.QuestionItem
import com.github.ebrahimi16153.trivia.network.QuestionApi
import com.github.ebrahimi16153.trivia.util.data.DataOrException
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api:QuestionApi) {

    private val listOfQuestion = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()


    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {

        try {
            listOfQuestion.isLoading = true
            listOfQuestion.data = api.getAllQuestion()
            if (listOfQuestion.data.toString().isNotEmpty()) {
                listOfQuestion.isLoading = false
            }
        } catch (ext: Exception) {
            listOfQuestion.e = ext
            Log.e("EXT", "getAllQuestions: ${listOfQuestion.e!!.localizedMessage}")
        }

        return listOfQuestion

    }


}