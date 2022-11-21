package com.github.ebrahimi16153.trivia.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.ebrahimi16153.trivia.component.Questions
import com.github.ebrahimi16153.trivia.util.myColor
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
            .fillMaxSize(), color = myColor().background
    ) {
        Column(
            modifier = Modifier.padding(top = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            QuestionTracker()

        }
    }
}

//@Preview
@Composable
fun QuestionTracker(counter: Int = 10, outOff: Int = 100) {

    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
            withStyle(
                style = SpanStyle(
                    color = myColor().text,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp
                )
            ) {

                append("Question $counter/")
                withStyle(
                    SpanStyle(
                        color = myColor().text,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                ) {
                    append("$outOff")
                }

            }
        }

    }, modifier = Modifier.padding(20.dp))
    
}
