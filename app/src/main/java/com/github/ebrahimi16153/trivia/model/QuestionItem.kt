package com.github.ebrahimi16153.trivia.model



data class QuestionItem(
    val answer: String?, // True
    val category: String?, // world
    val choices: List<String?>?,
    val question: String? // LFD2 was banned in Australia.
)