package com.example.fastcalculatetraining.domain.models

data class GameSettings(
    val gameTime: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val maxSumValue: Int
) {

}