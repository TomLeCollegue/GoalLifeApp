package com.entreprisecorp.goallife

public class Task (textObjective : String, textDefinition : String, frequency : Frequency) {

    val objective : String = textObjective
    val definition : String = textDefinition
    val frequency : Frequency = frequency
    val done : Boolean = false

    enum class Frequency {
        DAILY, WEEKLY, MONTHLY
    }
}