package com.example.login.service

import com.example.login.entity.User

interface userService {

    fun findByEmaiAndPassword(array: MutableList<User>,  email : String , password : String) : User?

}