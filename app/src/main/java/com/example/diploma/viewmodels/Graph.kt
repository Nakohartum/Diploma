package com.example.diploma.viewmodels

import android.content.Context
import androidx.room.Room

object Graph {
    lateinit var database: AppDatabase

    val appRepository by lazy {
        AppRepository(
            userDao = database.userDao(),
            subjectDataDao = database.subjectDataDao(),
            targetDao = database.targetDao(),
            teacherDao = database.teacherDao()
        )
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, AppDatabase::class.java, "app.db").build()
    }
}