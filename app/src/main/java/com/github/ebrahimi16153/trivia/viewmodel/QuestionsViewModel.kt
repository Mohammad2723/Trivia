package com.github.ebrahimi16153.trivia.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.trivia.model.QuestionItem
import com.github.ebrahimi16153.trivia.repository.QuestionRepository
import com.github.ebrahimi16153.trivia.util.data.DataOrException
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionsViewModel @Inject constructor(private val repository: QuestionRepository) :
    ViewModel() {

    private val data: MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        getAllQuestions()
    }

    private fun getAllQuestions() {

        viewModelScope.launch {
            data.value.isLoading = true
            data.value = repository.getAllQuestions()
            if (data.value.data.toString().isNotEmpty()) {
                data.value.isLoading = false
            }


        }


    }


}