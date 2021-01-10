package com.entreprisecorp.goallife

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Notofication_receiver : BroadcastReceiver(){

    val CHANNEL_ID = "123"
    val CHANNEL_NAME = "channel1"

    override fun onReceive(p0: Context?, p1: Intent?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_NAME
            val descriptionText = "desc"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                App.applicationContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }



        val intent = Intent(App.applicationContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val pendingIntent = PendingIntent.getActivity(App.applicationContext(),0,intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(App.applicationContext(), CHANNEL_ID)
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
                builder.setContentText(App.listTask[i-1].needToBeDoneFor())
                notify(i, builder.build())
            }
        }
    }



}
