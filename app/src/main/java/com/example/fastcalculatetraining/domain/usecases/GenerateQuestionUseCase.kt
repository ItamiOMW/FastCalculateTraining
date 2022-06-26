package com.example.fastcalculatetraining.domain.usecases

import com.example.fastcalculatetraining.domain.models.Question
import com.example.fastcalculatetraining.domain.repository.Repository

class GenerateQuestionUseCase(private val repository: Repository) {
    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    companion object {

        private const val COUNT_OF_OPTIONS = 6
    }
}