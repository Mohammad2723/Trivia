package com.github.ebrahimi16153.trivia.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.ebrahimi16153.trivia.component.Questions
import com.github.ebrahimi16153.trivia.component.myColor
import com.github.ebrahimi16153.trivia.viewmodel.QuestionsViewModel


@Composable
fun TriviaHome(viewModel: QuestionsViewModel = hiltViewModel()) {

    Questions(viewModel = viewModel)
    TriviaDisplay()

}

@Preview
@Composable
fun TriviaDisplay() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), color = myColor().background
    ) {

        Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {

        QuestionTracker()

        }


    }
}


@Composable
fun QuestionTracker(){

}
