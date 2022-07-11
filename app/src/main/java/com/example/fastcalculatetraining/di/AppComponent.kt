package com.example.fastcalculatetraining.di

import android.app.Application
import com.example.fastcalculatetraining.presentation.GameFinishedFragment
import com.example.fastcalculatetraining.presentation.GameFragment
import com.example.fastcalculatetraining.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(gameFragment: GameFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}