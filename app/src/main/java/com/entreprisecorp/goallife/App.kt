package com.entreprisecorp.goallife

import android.app.Application
import android.content.Context
import android.content.Intent

class App : Application() {

    init {
        instance = this
        listTask.add(Task("Faire une séance de sport", "Jambes + les bibis", Task.Frequency.WEEKLY))
        listTask.add(Task("Lire un livre", "", Task.Frequency.MONTHLY))
        listTask.add(Task("Regarder les mails", "", Task.Frequency.DAILY))
        listTask.add(Task("Appeller mamie", "le soir avant 19h", Task.Frequency.MONTHLY))
        listTask.add(Task("Appeller les darons", "avant 20 heures", Task.Frequency.WEEKLY))
    }

    companion object {
        private var instance: App? = null
        val listTask = ArrayList<Task>()

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }



    override fun onCreate() {
        super.onCreate()
        // initialize for any

        // Use ApplicationContext.
        // example: SharedPreferences etc...
        val context: Context = App.applicationContext()
        val sharedPreferences = getSharedPreferences("Goal", Context.MODE_PRIVATE)

        if(sharedPreferences.getString("NAME", null) == null){
            val intent = Intent(this, LogInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        else{
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

    }

}