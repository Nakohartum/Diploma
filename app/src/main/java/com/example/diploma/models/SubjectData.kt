package com.example.diploma.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Entity("subjects")
data class SubjectData(
    @PrimaryKey(autoGenerate = true) val subjectId: Long = 0L,
    @ColumnInfo("subject_name") val subjectName: String,
    @ColumnInfo("subject_description") val subjectDescription: String,
    @ColumnInfo("teacherId") val teacherID: Long,
    @ColumnInfo("subject_icon") val subjectIcon: ByteArray?,
    @ColumnInfo("subject_day") val dayOfWeek: Int,
    @ColumnInfo("userId") val userId: Long,
    @ColumnInfo("last_opened_time") val lastOpenedTime: Long
)

@Entity("targets")
data class TargetData(
    @PrimaryKey(autoGenerate = true) val targetId: Long = 0,
    @ColumnInfo("target_name") val targetName: String,
    @ColumnInfo("target_description") val targetDescription: String,
    @ColumnInfo("is_done") var isDone: Boolean = false,
    @ColumnInfo("target_deadline") var targetDeadline: Long,
    @ColumnInfo("subjectId") val subjectID: Long
)

@Dao
interface SubjectDataDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertSubject(vararg subject: SubjectData)

    @Update
    abstract fun updateSubject(vararg subject: SubjectData)

    @Delete
    abstract fun deleteSubject(vararg subject: SubjectData)

    @Query("SELECT * FROM subjects WHERE userId = :userId ORDER BY last_opened_time DESC LIMIT 1")
    abstract fun getLastOpenedLesson(userId: Long): SubjectData

    @Query("UPDATE subjects SET last_opened_time = :timestamp WHERE subjectId = :subjectId AND userId = :userId")
    abstract fun updateLastOpenedLesson(subjectId: Long, userId: Long, timestamp: Long)
    @Query("SELECT * FROM subjects WHERE userId = :userId")
    abstract fun getSubjectsFromUser(userId: Long): List<SubjectData>
    @Query("SELECT * FROM subjects WHERE subjectId = :subjectId")
    abstract fun getSubject(subjectId: Long): SubjectData?
    @Query("SELECT * FROM subjects WHERE subject_day = :id and userId = :userId")
    abstract fun getSubjectByDay(id: Int, userId: Int): Flow<List<SubjectData>>
}

@Dao
interface TargetDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertTarget(vararg targetData: TargetData)

    @Update
    abstract fun updateTarget(vararg target: TargetData)

    @Delete
    abstract fun deleteTarget(vararg target: TargetData)
    @Query("SELECT * FROM targets WHERE subjectId = :subjectId")
    abstract fun getTargets(subjectId: Long): Flow<List<TargetData>>
}