package com.entreprisecorp.goallife

import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("Goal", Context.MODE_PRIVATE)
        textHello.text = "Hello " + sharedPreferences.getString("NAME", "Boy") + " !"

        bottom_bar.onTabSelected = {
            when (it.id) {

                R.id.menu1 -> {
                    title_home.text = "Mes Objectifs"
                }
                R.id.menu2 -> {
                    title_home.text = "Aujourd'hui"
                }
                R.id.menu3 -> {
                    title_home.text = "Paramètres"
                }

            }
        }
    }
}