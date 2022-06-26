package com.example.fastcalculatetraining.domain.usecases

import com.example.fastcalculatetraining.domain.models.GameSettings
import com.example.fastcalculatetraining.domain.models.Level
import com.example.fastcalculatetraining.domain.repository.Repository

class GetGameSettingsUseCase(private val repository: Repository) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}