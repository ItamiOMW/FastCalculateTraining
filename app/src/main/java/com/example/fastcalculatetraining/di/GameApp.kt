package com.example.fastcalculatetraining.di

import android.app.Application

class GameApp: Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}