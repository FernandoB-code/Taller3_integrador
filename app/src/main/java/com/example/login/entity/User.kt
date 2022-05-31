package com.example.login.entity

import android.os.Parcel
import android.os.Parcelable

data class User(
    var firstName : String,
    var lastName : String,
    var dni : String,
    var email: String,
    var password: String,
    var account: Account

    )
{

    constructor() : this(
            "",
            "",
            "",
            "",
            "",
            Account(0,0.0)
           )
}


   /* override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}*/
