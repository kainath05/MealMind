package com.example.mealmind.data

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

class SharedViewModel : ViewModel() {
    var email by mutableStateOf("")
        private set

    var pictureId by mutableIntStateOf(0)
        private set

    var userId by mutableIntStateOf(0)
        private set

    fun updateEmail(newEmail: String) {
        email = newEmail
    }

    fun updatePictureId(newPictureId: Int){
        pictureId = newPictureId
    }

    fun updateUserId(newUserId: Int){
        userId = newUserId
    }
}

