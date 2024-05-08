package com.example.diploma.models

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity("teachers")
data class TeacherData(
    @PrimaryKey(autoGenerate = true) val teacherId: Long = 0,
    @ColumnInfo("teacher_name") val teacherName: String,
    @ColumnInfo("teacher_phone") val teachersPhone: String,
    @ColumnInfo("teacher_email") val teacherEmail: String = "",
    @ColumnInfo("user_id") val userId: Long
)

@Dao
interface TeacherDao{
    @Insert
    abstract fun addTeacher(vararg teacherData: TeacherData)

    @Delete
    abstract fun deleteTeacher(vararg teacherData: TeacherData)

    @Update
    abstract fun updateTeacher(vararg teacherData: TeacherData)

    @Query("SELECT * FROM teachers where user_id = :userId")
    abstract fun getTeachers(userId: Long): List<TeacherData>
    @Query("SELECT * FROM teachers where teacherId = :teacherID")
    abstract fun getTeacher(teacherID: Long): TeacherData?
}
