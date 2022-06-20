package com.example.login.entity

import java.text.DecimalFormat

data class Convert(
    var result: String,
    var documentation: String,
    var terms_of_use: String,
    var time_last_update_unix: Number,
    var time_last_update_utc: String,
    var time_next_update_unix: Number,
    var time_next_update_utc:String,
    var base_code: String,
    var target_code: String,
    var conversion_rate: Number,
    var conversion_result: Number
)
