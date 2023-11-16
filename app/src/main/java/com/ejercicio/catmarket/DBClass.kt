package com.ejercicio.catmarket

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class DBClass(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "CatMarket"

        // tabla usuarios
        private val TABLE_USERS = "users"
        private val KEY_NAME = "name"
        private val KEY_UNAME = "username"
        private val KEY_PSWD = "pswd"

        // tabla reportes
        private val TABLE_REPORTS = "report"
        private val KEY_REPORT_ID = "id"
        private val KEY_REPORT_TYPE = "type"
        private val KEY_REPORT_DESCRIPTION = "description"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Crear la tabla usuarios
        val newtb = ("CREATE TABLE " + TABLE_USERS + "("
                + KEY_NAME + " TEXT PRIMARY KEY,"
                + KEY_UNAME + " TEXT,"
                + KEY_PSWD + " TEXT" + ")")
        db?.execSQL(newtb)

        // Agregar la relación entre usuarios y reportes mediante 'username'
        val createReportsTable = ("CREATE TABLE $TABLE_REPORTS ("
                + "$KEY_REPORT_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$KEY_UNAME TEXT,"  // Agregar la relación con 'username'
                + "$KEY_REPORT_TYPE TEXT,"
                + "$KEY_REPORT_DESCRIPTION TEXT,"
                + "FOREIGN KEY($KEY_UNAME) REFERENCES $TABLE_USERS($KEY_UNAME))")
        db?.execSQL(createReportsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS)
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORTS)
        onCreate(db)
    }

    @SuppressLint("Range")
    fun getReportsForUser(username: String): List<Report> {
        val reports = mutableListOf<Report>()
        val selectQuery = "SELECT * FROM $TABLE_REPORTS WHERE $KEY_UNAME = '$username'"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val report = Report(
                    cursor.getString(cursor.getColumnIndex(KEY_REPORT_TYPE)),
                    cursor.getString(cursor.getColumnIndex(KEY_REPORT_DESCRIPTION))
                )
                reports.add(report)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return reports
    }

    fun saveReport(username: String, reportType: String, reportDescription: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_UNAME, username)  // Asocia el informe al usuario actual
        values.put(KEY_REPORT_TYPE, reportType)
        values.put(KEY_REPORT_DESCRIPTION, reportDescription)
        db.insert(TABLE_REPORTS, null, values)
        db.close()
    }




    data class User(
        val name: String  = KEY_NAME,
        val username: String = KEY_UNAME,
        val pswd: String = KEY_PSWD
    )

    data class Report(
        val type: String,
        val description: String
    )

}