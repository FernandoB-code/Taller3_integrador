package com.example.login.entity

import java.util.*

class Transaction (
        var userTo: String,
        var userFrom: String,
        var ammount : Double,
        var date : String?
        ) {

        constructor() : this(
                "",
                "",
                0.0,
                ""

        )
}


