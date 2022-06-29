package com.example.fastcalculatetraining.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class GameSettings(
    val gameTime: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val maxSumValue: Int
): Parcelable{

}