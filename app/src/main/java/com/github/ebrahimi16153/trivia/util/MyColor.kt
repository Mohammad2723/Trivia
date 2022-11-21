package com.github.ebrahimi16153.trivia.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.github.ebrahimi16153.trivia.ui.theme.*


@Composable
fun myColor(darkTheme: Boolean = isSystemInDarkTheme()): TriviaColor {

    val lightColor = TriviaColor(
        background = lightBackground,
        primary = lightPrimary,
        text = lightText,
        successes = lightSuccess,
        fail = lightFail
    )
    val darkColor = TriviaColor(
        background = darkBackground,
        primary = darkPrimary,
        text = darkText,
        successes = darkSuccess,
        fail = darkFail
    )


    return if (darkTheme){
        darkColor
    }else{
        lightColor
    }

}
