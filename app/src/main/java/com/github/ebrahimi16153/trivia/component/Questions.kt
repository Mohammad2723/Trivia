package com.github.ebrahimi16153.trivia.component

import android.util.Log
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.github.ebrahimi16153.trivia.model.Question
import com.github.ebrahimi16153.trivia.viewmodel.QuestionsViewModel


@Composable
fun Questions(viewModel: QuestionsViewModel) {
    val question = viewModel.data.value.data?.toMutableList()

    if (viewModel.data.value.isLoading == true) {
        CircularProgressIndicator(color = Color(0xFF007AFF))
        Log.d("Loading...", "LOADING >>>>")

    } else {
        Log.d("loading...", "LOADING Successfully >>>>")
        question?.forEach {
            Log.d("Result", it.question.toString())
        }

    }
}


@Composable
fun QuestionsDisplay (){
    Surface() {

    }
}
