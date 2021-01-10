package com.entreprisecorp.goallife

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.entreprisecorp.goallife.fragments.SettingsFragment
import com.entreprisecorp.goallife.fragments.TaskFragment
import com.entreprisecorp.goallife.fragments.TodayFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("Goal", Context.MODE_PRIVATE)

        setFragment(TodayFragment())

        bottom_bar.onTabSelected = {
            when (it.id) {

                R.id.menu1 -> {
                    title_home.text = "Mes objectifs"
                    setFragment(TaskFragment())
                    mainImage.setImageResource(R.drawable.clipboard)
                }
                R.id.menu2 -> {
                    title_home.text = "Aujourd'hui "
                    setFragment(TodayFragment())
                    mainImage.setImageResource(R.drawable.calendar)
                }
                R.id.menu3 -> {
                    title_home.text = "Paramètres"
                    setFragment(SettingsFragment())
                    mainImage.setImageResource(R.drawable.settings)
                }

            }
        }

        val task = Task("okok", "okokokok", Task.Frequency.DAILY)


    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction =
            fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, fragment)
        fragmentTransaction.commit()
    }




}