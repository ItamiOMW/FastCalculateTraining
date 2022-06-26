package com.example.fastcalculatetraining.data.repositoryImpl

import com.example.fastcalculatetraining.domain.models.GameSettings
import com.example.fastcalculatetraining.domain.models.Level
import com.example.fastcalculatetraining.domain.models.Question
import com.example.fastcalculatetraining.domain.repository.Repository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class RepositoryImpl: Repository {

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val from = max(visibleNumber - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, visibleNumber + countOfOptions)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when(level) {
            Level.TEST -> GameSettings(
                10,
                5,
                50,
                30
            )
            Level.EASY -> GameSettings(
                30,
                10,
                70,
                20
            )
            Level.NORMAL -> GameSettings(
                30,
                15,
                70,
                30
            )
            Level.HARD -> GameSettings(
                25,
                15,
                70,
                50
            )
        }
    }

    companion object {

        private const val MIN_SUM_VALUE = 3
        private const val MIN_ANSWER_VALUE = 1

    }
}