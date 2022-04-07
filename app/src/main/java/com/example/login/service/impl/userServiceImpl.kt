package com.example.login.service.impl

import com.example.login.entity.User
import com.example.login.service.userService

class userServiceImpl : userService {

    override fun findByEmaiAndPassword(userList: MutableList<User>,  email : String , password : String) : User? {
//
        var isValidUser : Boolean = false
        var index : Int = 0
        var userFound: User? = null

        while(index < userList.size && !isValidUser) {

            var actual : User = userList.get(index = index)

            if(actual.email.equals(email) && actual.password.equals(password))  {
                isValidUser = true;
                userFound =  actual
            }   else {
                index++
            }
        }
        return userFound
    }

    }
