package com.ejercicio.catmarket

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

        //var value=intent.getStringExtra("name")
        //bind.uname.text=value
        //bind.logout.setOnClickListener {
        //    startActivity(Intent(this,login_form::class.java))
        //}
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }

}