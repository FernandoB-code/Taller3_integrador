package com.example.login.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.entity.User
import com.example.login.repository.UserRepository

class ProfileViewModel : ViewModel() {
    var userLiveData: MutableLiveData<User> = MutableLiveData()

    fun getUser(email: String?) {
        if (email.isNullOrBlank()) return
        UserRepository.getUser(userLiveData, email)
    }

}