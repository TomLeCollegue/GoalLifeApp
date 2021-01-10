package com.entreprisecorp.goallife

import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.getInstance

public class Task (textObjective : String, textDefinition : String, frequency : Frequency) {

    var objective : String = textObjective
    var definition : String = textDefinition
    var frequency : Frequency = frequency
    var id = 0
    var dateDone = getInstance().time.toString("dd/MM/yyyy")


    companion object{
        var today = getInstance().time.toString("dd/MM/yyyy")

        fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
            val formatter = SimpleDateFormat(format, locale)
            return formatter.format(this)
        }
    }

    enum class Frequency {
        DAILY, WEEKLY, MONTHLY
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun resetDate(){
        //dateDone = getInstance().time.toString("yyyy/MM/dd")
        dateDone = today
    }


    fun getDays() : Int {
        val date = SimpleDateFormat("dd/MM/yyyy").parse(dateDone)
        val todayDate = SimpleDateFormat("dd/MM/yyyy").parse(today)
        val daysBetween = ( (todayDate.time - date.time) / (1000 * 60 * 60 * 24)).toInt()
        var days : Int

        when (frequency) {
            Frequency.DAILY -> {
                days = 1-daysBetween
            }
            Frequency.WEEKLY -> {
                days = 7 - daysBetween
            }
            Frequency.MONTHLY -> {
                days = 30 - daysBetween
            }
        }

        return days
    }

    fun needToBeDoneFor() : String{
        val days = getDays()
        var sentence : String
        when (frequency) {
            Frequency.DAILY -> {
                if(days==1){
                    sentence = "A faire pour demain "
                }
                else if(days == 0){
                    sentence = "A faire aujourd'hui "
                }
                else{
                    sentence = "Vous avez "+ -days + " jours de retard "
                }
            }
            Frequency.WEEKLY -> {
                if(days==1){
                    sentence = "A faire aujourd'hui "
                }
                else if(days == 2){
                    sentence = "A faire pour demain "
                }
                else if(days < 0){
                    sentence = "Vous avez "+ -days + " jours de retard "
                }
                else{
                    sentence = "A faire pour dans " + days + " jours "
                }
            }
            Frequency.MONTHLY -> {
                if(days==1){
                    sentence = "A faire aujourd'hui "
                }
                if(days == 2){
                    sentence = "A faire pour demain "
                }
                else if(days < 0){
                    sentence = "Vous avez "+ -days + " jours de retard "
                }
                else{
                    sentence = "A faire pour dans " + days + " jours  "
                }
            }
        }

        return sentence
    }

}

