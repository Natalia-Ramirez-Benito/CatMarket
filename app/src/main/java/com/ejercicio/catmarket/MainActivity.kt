package com.ejercicio.catmarket

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ejercicio.catmarket.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var dbhelp=DBClass(applicationContext)
        var db=dbhelp.writableDatabase
        binding.btnrgs.setOnClickListener {
            var name=binding.ed1.text.toString()
            var username=binding.ed2.text.toString()
            var password=binding.ed3.text.toString()
            if(name.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
                var data = ContentValues()
                data.put("name", binding.ed1.text.toString())
                data.put("username", binding.ed2.text.toString())
                data.put("pswd", binding.ed3.text.toString())
                var rs:Long = db.insert("users", null, data)
                if(!rs.equals(-1)) {
                    var ad = AlertDialog.Builder(this)
                    ad.setTitle("Mensaje")
                    ad.setMessage("Se ha registrado correctamente")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                    binding.ed1.text.clear()
                    binding.ed2.text.clear()
                    binding.ed3.text.clear()
                }else{
                    var ad = AlertDialog.Builder(this)
                    ad.setTitle("Error")
                    ad.setMessage("No se ha podido crear cuenta.")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                    binding.ed1.text.clear()
                    binding.ed2.text.clear()
                    binding.ed3.text.clear()
                }
            }else{
                Toast.makeText(this,"Faltan datos por rellenar", Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginLink.setOnClickListener {
            val intent= Intent(this,login_form::class.java)
            startActivity(intent)
        }

    }
}