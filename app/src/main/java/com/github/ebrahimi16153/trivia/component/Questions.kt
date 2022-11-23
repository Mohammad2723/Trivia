package com.github.ebrahimi16153.trivia.component

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ebrahimi16153.trivia.model.QuestionItem
import com.github.ebrahimi16153.trivia.util.myColor
import com.github.ebrahimi16153.trivia.viewmodel.QuestionsViewModel


@Composable
fun Questions(viewModel: QuestionsViewModel) {
    val question = viewModel.data.value.data?.toMutableList()

    if (viewModel.data.value.isLoading == true) {
        CircularProgressIndicator(color = Color(0xFF007AFF))


    } else {
        Log.d("loading...", "LOADING Successfully >>>>")
//        question?.forEach {
//            Log.d("Result", it.question.toString())
//            
//        }
        question?.first()?.let { QuestionDisplay(question = it) }

    }
}


//@Preview
@Composable
fun QuestionDisplay(
    question: QuestionItem,
//    questionIndex: MutableState<Int>,
//    viewModel: QuestionsViewModel,
    onNextClick: (Int) -> Unit = {}
) {

    val choiceState = remember {
        question.choices?.toMutableList()
    }
    val answerState = remember {
        mutableStateOf<Int?>(null)
    }
    val correctAnswerState = remember {
        mutableStateOf<Boolean?>(null)
    }
    val updateAnswer: (Int) -> Unit = remember(question) {
        {
            answerState.value = it
            correctAnswerState.value = choiceState?.get(it) == question.answer
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(), color = myColor().background
    ) {
        Column(
            modifier = Modifier.padding(all = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            QuestionTracker()
//            DottedLine( )
            Line()

            Column(modifier = Modifier.padding(top = 20.dp)) {
                question.question?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.3f)
                            .align(alignment = Alignment.Start),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 22.sp,
                        color = myColor().text
                    )
                }
            }

            // list of radio button -> choices
            choiceState?.forEachIndexed { index, answerTex ->

                Row(
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                        .height(45.dp)
                        .border(
                            width = 4.dp,
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    myColor().primary,
                                    myColor().fail
                                )
                            ), shape = RoundedCornerShape(15.dp)
                        )
                        .clip(
                            RoundedCornerShape(
                                topStartPercent = 50,
                                topEndPercent = 50,
                                bottomEndPercent = 50,
                                bottomStartPercent = 50
                            )
                        )
                        .background(Color.Transparent),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (answerState.value == index),
                        onClick = { updateAnswer(index) },
                        modifier = Modifier.padding(start = 16.dp),
                        colors = RadioButtonDefaults
                            .colors(
                                selectedColor =
                                if (correctAnswerState.value == true && index == answerState.value) {
                                    myColor().successes
                                } else {
                                    myColor().fail
                                }))
                    Text(text = answerTex!!)

                }
            }
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

@Composable
fun DottedLine(pathEffect: PathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)) {
    val color: Color = myColor().text
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp), onDraw = {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, y = 0f),
            pathEffect = pathEffect
        )
    })

}

@Composable
fun Line() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(0.5.dp)
            .alpha(0.7f), color = myColor().text
    ) {

    }
}