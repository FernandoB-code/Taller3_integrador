package com.example.login.repository


import com.example.login.entity.User

class UserRepository {

    var userList : MutableList<User> = mutableListOf()

    init {

    userList.add(User("Fernando","test@test.com","test"))
    userList.add(User("Test","1","1"))

    }

}