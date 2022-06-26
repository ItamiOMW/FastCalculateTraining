package com.example.fastcalculatetraining.domain.repository

import com.example.fastcalculatetraining.domain.models.GameSettings
import com.example.fastcalculatetraining.domain.models.Level
import com.example.fastcalculatetraining.domain.models.Question

interface Repository {

    fun getGameSettings(level: Level): GameSettings

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int,
    ): Question
}