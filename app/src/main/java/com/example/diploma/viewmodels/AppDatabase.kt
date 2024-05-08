package com.example.diploma.viewmodels

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.diploma.models.SubjectData
import com.example.diploma.models.SubjectDataDao
import com.example.diploma.models.TargetDao
import com.example.diploma.models.TargetData
import com.example.diploma.models.TeacherDao
import com.example.diploma.models.TeacherData
import com.example.diploma.models.UserDao
import com.example.diploma.models.UserData

@Database(
    entities = [UserData::class, SubjectData::class, TargetData::class, TeacherData::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun subjectDataDao(): SubjectDataDao
    abstract fun targetDao(): TargetDao
    abstract fun teacherDao(): TeacherDao
}