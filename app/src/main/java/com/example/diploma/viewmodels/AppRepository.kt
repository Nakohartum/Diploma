package com.example.diploma.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diploma.models.SubjectData
import com.example.diploma.models.SubjectDataDao
import com.example.diploma.models.TargetDao
import com.example.diploma.models.TargetData
import com.example.diploma.models.TeacherDao
import com.example.diploma.models.TeacherData
import com.example.diploma.models.UserDao
import com.example.diploma.models.UserData
import kotlinx.coroutines.flow.Flow

class AppRepository(
    private val userDao: UserDao,
    private val subjectDataDao: SubjectDataDao,
    private val targetDao: TargetDao,
    private val teacherDao: TeacherDao
) {
    suspend fun addSubject(subjectData: SubjectData){
        subjectDataDao.insertSubject(subjectData)
    }

    suspend fun addTarget(targetData: TargetData){
        targetDao.insertTarget(targetData)
    }

    suspend fun addTeacher(teacherData: TeacherData){
        teacherDao.addTeacher(teacherData)
    }

    suspend fun addUser(userData: UserData) {
        userDao.addUser(userData)
    }

    suspend fun removeSubject(subjectData: SubjectData){
        subjectDataDao.deleteSubject(subjectData)
    }

    suspend fun removeTarget(targetData: TargetData){
        targetDao.deleteTarget(targetData)
    }

    suspend fun removeTeacher(teacherData: TeacherData){
        teacherDao.deleteTeacher(teacherData)
    }

    suspend fun updateSubject(subjectData: SubjectData){
        subjectDataDao.updateSubject(subjectData)
    }

    suspend fun updateTarget(targetData: TargetData){
        targetDao.updateTarget(targetData)
    }

    suspend fun updateTeacher(teacherData: TeacherData){
        teacherDao.updateTeacher(teacherData)
    }

    suspend fun updateLastOpened(subjectId: Long, userId: Long, timestamp: Long){
        subjectDataDao.updateLastOpenedLesson(subjectId, userId, timestamp)
    }

    suspend fun getSubjectsFromUser(userId: Long): List<SubjectData>{
        return subjectDataDao.getSubjectsFromUser(userId)
    }

//    fun getSubject(subjectId: Int): Flow<List<SubjectWithTargets>>{
//        return subjectDataDao.getSubjectWithTargets(subjectId)
//    }

    suspend fun getTeachers(userId: Long): List<TeacherData> {
        return teacherDao.getTeachers(userId)
    }

    suspend fun getLastOpened(userId: Long): SubjectData{
        return subjectDataDao.getLastOpenedLesson(userId)
    }

    suspend fun getUser(userName: String): UserData {
        return userDao.getUser(userName)
    }

    suspend fun getSubject(subjectId: Long): SubjectData? {
        return subjectDataDao.getSubject(subjectId)
    }

    suspend fun getAllUsers(): List<UserData> {
        return userDao.getAllUsers()
    }

    suspend fun getTeacher(teacherID: Long): TeacherData? {
        return teacherDao.getTeacher(teacherID)
    }

    suspend fun getTargets(subjectId: Long): Flow<List<TargetData>> {
        return targetDao.getTargets(subjectId)
    }


    suspend fun deleteTarget(targetData: TargetData) {
        targetDao.deleteTarget(targetData)
    }

    fun getSubjectByDay(id: Int): Flow<List<SubjectData>> {
        return subjectDataDao.getSubjectByDay(id)
    }

}