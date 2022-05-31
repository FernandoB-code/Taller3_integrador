package com.example.login.repository


import com.example.login.entity.User
import com.example.login.entity.Account

class UserRepository {

    var userList : MutableList<User> = mutableListOf()

    init {

    userList.add(User(
        "Ayelen",
        "Centurion",
        "35000000",
        "test1@test.com",
        "test1",
        Account(58790,2000.0))
    )
    userList.add(User("Leandro",
        "Centurion",
        "37000000",
        "test2@test.com",
        "test2",
        Account(54873,3000.0))
    )

    }

}