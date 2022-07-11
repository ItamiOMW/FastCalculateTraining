package com.example.fastcalculatetraining.domain.usecases

import com.example.fastcalculatetraining.domain.models.GameSettings
import com.example.fastcalculatetraining.domain.models.Level
import com.example.fastcalculatetraining.domain.repository.Repository
import javax.inject.Inject

class GetGameSettingsUseCase @Inject constructor (private val repository: Repository) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}