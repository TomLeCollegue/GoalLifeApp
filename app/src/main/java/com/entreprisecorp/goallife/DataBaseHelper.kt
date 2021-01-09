package com.entreprisecorp.goallife

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.fixedRateTimer

val DATABASENAME = "database"
class DataBaseHelper(var context: Context) : SQLiteOpenHelper(context, DATABASENAME,null, 1) {

    val TABLENAME = "Tasks"
    val COL_NAME = "name"
    val COL_DEF = "def"
    val COL_ID = "id"
    val COL_DONE = "done"
    val COL_FREQUENCY = "frequency"


    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE IF NOT EXISTS TABLE " + TABLENAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " VARCHAR(256)," + COL_DEF + " VARCHAR(256)," + COL_DONE + " VARCHAR(256), " + COL_FREQUENCY + " VARCHAR(256))"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //onCreate(db);
    }

    fun insertData(task: Task) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, task.objective)
        contentValues.put(COL_DEF, task.definition)
        contentValues.put(COL_FREQUENCY, task.frequency.toString())


        contentValues.put(COL_DONE, task.done.toString())
        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun readData(): ArrayList<Task> {
        App.listTask.clear()
        val list: ArrayList<Task> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val task = Task("","",Task.Frequency.WEEKLY)
                task.objective = result.getString(result.getColumnIndex(COL_NAME))
                task.definition = result.getString(result.getColumnIndex(COL_DEF)).toString()
                task.done = result.getString(result.getColumnIndex(COL_DONE)).toString()
                task.id = result.getString(result.getColumnIndex(COL_ID)).toInt()

                when(result.getString(result.getColumnIndex(COL_FREQUENCY)).toString()){
                    "DAILY" ->{
                        task.frequency = Task.Frequency.DAILY
                    }
                    "WEEKLY" ->{
                        task.frequency = Task.Frequency.WEEKLY
                    }
                    "MONTHLY" ->{
                        task.frequency = Task.Frequency.MONTHLY
                    }

                }
                App.listTask.add(task)
            }
            while (result.moveToNext())
        }
        return list
    }


}

