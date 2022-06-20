package com.example.login.entity

import android.os.Parcel
import android.os.Parcelable
import java.util.*
import java.util.Random
import kotlin.random.Random.Default.nextInt


data class User(
    var id: String,
    var name: String,
    var dni: String,
    var email: String,
    //var password: String,
    //var account: Account?
    var accountID: String

    )
{

    constructor() : this(
            "",
            "",
            "",
            "",
            ""
           )


}




