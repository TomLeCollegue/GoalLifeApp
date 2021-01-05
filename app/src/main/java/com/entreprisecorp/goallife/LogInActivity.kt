package com.entreprisecorp.goallife

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)


        val sharedPreferences = getSharedPreferences("Goal", Context.MODE_PRIVATE)
        val startButton = findViewById<Button>(R.id.buttonStart)
        val inputName = findViewById<TextView>(R.id.nameText)

        startButton.setOnClickListener{
            val name = inputName.text.toString()
            val editor = sharedPreferences.edit()
            editor.putString("NAME", name)
            editor.apply()

            if(name != ""){
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }





    }
}