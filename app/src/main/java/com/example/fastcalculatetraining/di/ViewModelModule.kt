package com.example.fastcalculatetraining.di

import androidx.lifecycle.ViewModel
import com.example.fastcalculatetraining.presentation.GameViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    fun bindGameViewModel(gameViewModel: GameViewModel): ViewModel
}