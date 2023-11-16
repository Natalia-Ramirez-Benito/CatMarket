package com.ejercicio.catmarket

import ReportFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ejercicio.catmarket.databinding.ActivityWelcomeWindowBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class welcome_window : AppCompatActivity() {
    private lateinit var bind: ActivityWelcomeWindowBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityWelcomeWindowBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val username = intent.getStringExtra("name") ?: ""

        bottomNavigationView = bind.bottomNavigation
        replaceFragment(HomeFragment())
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.bottom_report -> {
                    replaceFragment(ReportFragment())
                    true
                }
                R.id.bottom_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        // Obtener el nombre de usuario del Intent
        val username = intent.getStringExtra("name") ?: ""

        // Pasar el nombre de usuario al fragmento utilizando el método newInstance
        val newFragment = when (fragment) {
            is HomeFragment -> HomeFragment.newInstance(username)
            is ReportFragment -> ReportFragment.newInstance(username)
            is ProfileFragment -> ProfileFragment.newInstance(username)
            else -> fragment
        }

        supportFragmentManager.beginTransaction().replace(R.id.frame_container, newFragment).commit()
    }

}