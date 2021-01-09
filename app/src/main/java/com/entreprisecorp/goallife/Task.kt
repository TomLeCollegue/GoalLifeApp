package com.entreprisecorp.goallife

public class Task (textObjective : String, textDefinition : String, frequency : Frequency) {

    var objective : String = textObjective
    var definition : String = textDefinition
    var frequency : Frequency = frequency
    val done : Boolean = false

    enum class Frequency {
        DAILY, WEEKLY, MONTHLY
    }
}