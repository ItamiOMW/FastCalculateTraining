package com.example.fastcalculatetraining.domain.models

data class Question(
    val sum: Int,
    val visibleNumber: Int,
    val options: List<Int>
) {

}