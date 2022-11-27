package com.github.ebrahimi16153.trivia.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
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
    val questions = viewModel.data.value.data?.toMutableList()
    val questionSize: Int? = viewModel.data.value.data?.size
    val questionIndex = remember { mutableStateOf(0) }

    if (viewModel.data.value.isLoading == true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(myColor().background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            CircularProgressIndicator(color = myColor().primary)
        }


    } else {
        val question = try {
            questions?.get(questionIndex.value)
        } catch (e: Exception) {
            null
        }

//        Log.d("loading...", "LOADING Successfully >>>>")
//        question?.forEach {
//            Log.d("Result", it.question.toString())
//            
//        }

        if (questions != null) {
            QuestionDisplay(
                question = question!!,
                questionIndex = questionIndex,
                viewModel = viewModel,
                questionSize = questionSize
            ) {
                questionIndex.value = questionIndex.value + 1
            }
        }

    }
}


//@Preview
@Composable
fun QuestionDisplay(
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    viewModel: QuestionsViewModel,
    questionSize: Int?,
    onNextClick: (Int) -> Unit
) {
    val choseState = remember(question) {
        question.choices?.toMutableStateList()
    }

    val answerState = remember(question) {
        mutableStateOf<Int?>(null)
    }
    val correctAnswerState = remember(question) {
        mutableStateOf<Boolean?>(null)
    }
    val updateAnswer: (Int) -> Unit = remember(question) {
        {
            answerState.value = it
            correctAnswerState.value = choseState?.get(it) == question.answer
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(), color = myColor().background
    ) {
        Column(
            modifier = Modifier.padding(all = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            QuestionTracker(counter = questionIndex.value + 1, outOff = questionSize)
            DottedLine( )
            QuestionContent(question)
            // list of radio button -> choices
//            AnswerContent(choiceState, answerState, updateAnswer, correctAnswerState)
            AnswerContent(choseState, answerState, updateAnswer, correctAnswerState)

            NextButton(onNextClick, questionIndex)
        }
    }


}

@Composable
private fun NextButton(
    onNextClick: (Int) -> Unit,
    questionIndex: MutableState<Int>
) {
    Button(
        onClick = { onNextClick(questionIndex.value) },
        modifier = Modifier.padding(3.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = myColor().primary),
        shape = RoundedCornerShape(50)
    ) {
        Text(text = "Next", color = Color(0xFFEFEFEF))

    }
}

@Composable
private fun AnswerContent(
    choseState: SnapshotStateList<String?>?,
    answerState: MutableState<Int?>,
    updateAnswer: (Int) -> Unit,
    correctAnswerState: MutableState<Boolean?>
) {
    choseState?.forEachIndexed { index, answerText ->
        Row(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .height(55.dp)
                .border(
                    width = 4.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            myColor().primary,
                            myColor().fail
                        )
                    ),
                    shape = RoundedCornerShape(15.dp)
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
                onClick = {
                    updateAnswer(index)
                },
                modifier = Modifier.padding(start = 16.dp),
                colors = RadioButtonDefaults
                    .colors(
                        selectedColor = if (correctAnswerState.value == true && index == answerState.value) {
                            myColor().successes
                        } else {
                            myColor().fail
                        }
                    )
            )
            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Light,
                        color = if (correctAnswerState.value == true && index == answerState.value) {
                            Color.Green
                        } else if (correctAnswerState.value == false && index == answerState.value) {
                            myColor().fail
                        } else {
                            myColor().successes
                        },
                        fontSize = 17.sp
                    )
                ) {

                    append(answerText!!)
                }
            }
            Text(text = annotatedString, modifier = Modifier.padding(6.dp))

        }
    }
}


//@Composable
//private fun AnswerContent(
//    choiceState: MutableList<String?>?,
//    answerState: MutableState<Int?>,
//    updateAnswer: (Int) -> Unit,
//    correctAnswerState: MutableState<Boolean?>
//) {
//
//}

@Composable
private fun QuestionContent(question: QuestionItem) {
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
}

//@Preview
@Composable
fun QuestionTracker(counter: Int = 10, outOff: Int? = 100) {

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
