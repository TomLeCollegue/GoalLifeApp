package com.entreprisecorp.goallife

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.util.*

public class Task (textObjective : String, textDefinition : String, frequency : Frequency) {

    var objective : String = textObjective
    var definition : String = textDefinition
    var frequency : Frequency = frequency

    val done = Calendar.getInstance().time

    enum class Frequency {
        DAILY, WEEKLY, MONTHLY
    }
}