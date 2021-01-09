package com.entreprisecorp.goallife

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import java.util.Calendar.getInstance

public class Task (textObjective : String, textDefinition : String, frequency : Frequency) {

    var objective : String = textObjective
    var definition : String = textDefinition
    var frequency : Frequency = frequency
    var id = 0
    var done = getInstance().time.toString("yyyy/MM/dd")

    enum class Frequency {
        DAILY, WEEKLY, MONTHLY
    }
    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }
}

