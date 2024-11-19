package com.example.mealmind.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mealmind.data.database.User
import com.example.mealmind.data.database.UserDao
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    fun insert(user: User) {
        viewModelScope.launch {
            userDao.insert(user)
        }
    }

    fun getUser(email: String, password: String = "", callback: (User?) -> Unit) {
        viewModelScope.launch {
            val user = userDao.getUser(email, password)
            callback(user)
        }
    }

    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }


    suspend fun updateProfileImage(email: String, imageId: Int) {
        userDao.updateProfileImage(email, imageId)
    }

    suspend fun hasUsers(): Boolean {
        return userDao.countUsers() > 0
    }

}

class UserViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
