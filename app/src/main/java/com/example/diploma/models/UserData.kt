package com.example.diploma.models

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Update
import com.example.diploma.R
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Entity(tableName = "users")
data class UserData(
    @PrimaryKey(autoGenerate = true) val userId: Long = 0L,
    @ColumnInfo(name = "user_name") val userName: String?,
    @ColumnInfo(name = "user_surname") val userSurname: String?,
    @ColumnInfo(name = "user_prof") val userProfession: String,
    @ColumnInfo(name = "user_course") val userCourse: Int,
    @ColumnInfo(name = "user_picture") val userPicture: String?,
)

@Dao
interface UserDao {
    @Insert
    abstract fun addUser(userData: UserData)

    @Update
    abstract fun updateUser(userData: UserData)

    @Query("SELECT * FROM users where user_name = :userName")
    abstract fun getUser(userName: String): UserData

    @Query("SELECT * FROM users")
    abstract fun getAllUsers(): List<UserData>

}

