package com.entreprisecorp.goallife.fragments

import android.app.*
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.INotificationSideChannel
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.entreprisecorp.goallife.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*
import java.util.*

class SettingsFragment : Fragment() {

        val CHANNEL_ID = "123"
        val CHANNEL_NAME = "channel1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        view.timeEdit.setText(Task.today)

        view.buttonChangeTime.setOnClickListener(){
            Task.today = timeEdit.text.toString()
        }
        return view
    }


    private fun activateNotif(){
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.HOUR_OF_DAY, 15)
        calendar.set(Calendar.MINUTE,50)
        calendar.set(Calendar.SECOND,0)
        val intent = Intent(App.applicationContext(), Notofication_receiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(App.applicationContext(),100,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        var alarmManager: AlarmManager = context?.getSystemService(ALARM_SERVICE) as AlarmManager

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
    }

    fun Notif() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_NAME
            val descriptionText = "desc"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(App.applicationContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val pendingIntent = PendingIntent.getActivity(App.applicationContext(),0,intent, PendingIntent.FLAG_UPDATE_CURRENT)

        var builder = NotificationCompat.Builder(App.applicationContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.calendar)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(App.applicationContext())) {
                val db =  DataBaseHelper(App.applicationContext())
                db.readData()
                for(i in 1..App.listTask.size){

                    builder.setContentTitle(App.listTask[i -1].objective)
                    notify(i, builder.build())
                }



            }
        }


}
