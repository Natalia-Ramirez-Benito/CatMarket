// UserManager.kt
package com.ejercicio.catmarket

import android.content.Context
import android.content.SharedPreferences

class UserManager(private val context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "UserPrefs"
        private const val KEY_USERNAME = "username"
    }

    fun loginUser(username: String) {
        sharedPreferences.edit().putString(KEY_USERNAME, username).apply()
    }

    fun logoutUser() {
        sharedPreferences.edit().remove(KEY_USERNAME).apply()
    }

    fun getCurrentUser(): DBClass.User? {
        val username = sharedPreferences.getString(KEY_USERNAME, null)
        return if (username != null) {
            // Simplemente devolvemos el nombre de usuario por ahora,
            // pero puedes recuperar más información del usuario según tus necesidades
            DBClass.User(username, username, "")
        } else {
            null
        }
    }
}
