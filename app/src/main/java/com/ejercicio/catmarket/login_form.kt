package com.ejercicio.catmarket

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.ejercicio.catmarket.databinding.ActivityLoginFormBinding

class login_form : AppCompatActivity() {
    private lateinit var bind : ActivityLoginFormBinding
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityLoginFormBinding.inflate(layoutInflater)
        setContentView(bind.root)
        var dbhelp=DBClass(applicationContext)
        var db=dbhelp.readableDatabase
        bind.btnlogin.setOnClickListener {
            var username=bind.eduser.text.toString();
            var password=bind.edpw.text.toString()
            val query="SELECT * FROM users WHERE username='"+username+"' AND pswd='"+password+"'"
            val rs=db.rawQuery(query,null)
            if(rs.moveToFirst()){
                val name=rs.getString(rs.getColumnIndex("name"))
                rs.close()
                startActivity(Intent(this,welcome_window::class.java).putExtra("name",name))
            }
            else{
                var ad = AlertDialog.Builder(this)
                ad.setTitle("Error")
                ad.setMessage("Usuario o contraseña incorrectas")
                ad.setPositiveButton("Ok", null)
                ad.show()
            }
        }
        bind.loginLink.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}