package com.example.mealmind.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mealmind.data.database.Preference
import com.example.mealmind.data.database.PreferenceDao
import kotlinx.coroutines.launch

class PreferenceViewModel(private val preferenceDao: PreferenceDao) : ViewModel() {

    fun insert(preference: Preference) {
        viewModelScope.launch {
            preferenceDao.insert(preference)
        }
    }

    fun getPreferencesByUserId(userId: Int, callback: (List<Preference>) -> Unit) {
        viewModelScope.launch {
            val preferences = preferenceDao.getPreferencesByUserId(userId)
            callback(preferences)
        }
    }
}

class PreferenceViewModelFactory(private val preferenceDao: PreferenceDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreferenceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PreferenceViewModel(preferenceDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
