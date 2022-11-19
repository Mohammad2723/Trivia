package com.github.ebrahimi16153.trivia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.ebrahimi16153.trivia.ui.theme.TriviaTheme
import com.github.ebrahimi16153.trivia.viewmodel.QuestionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // A surface container using the 'background' color from the theme
            MyApp()

        }
    }
}

@Composable
fun MyApp(viewModel: QuestionsViewModel = hiltViewModel()) {
    TriviaTheme {
        QuestionData(viewModel = viewModel)
    }

}

@Composable
fun QuestionData(viewModel: QuestionsViewModel) {
    val question = viewModel.data.value.data?.toMutableList()

    if (viewModel.data.value.isLoading == true) {

        Log.d("Loading...", "LOADING >>>>")

    } else {
        Log.d("loading...", "LOADING Successfully >>>>")
        question?.forEach {
            Log.d("Result", it.question.toString())
        }

    }


}


//
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    TriviaTheme {
//        Greeting("Android")
//    }
//}