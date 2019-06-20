package com.ondrnovy.open.chat.view

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: Date): String{

    val now = Date();

    val diff = now.getTime() - date.getTime()
    val diffDays = (diff / (24 * 60 * 60 * 1000)).toInt()
    val diffhours = (diff / (60 * 60 * 1000)).toInt()
    val diffmin = (diff / (60 * 1000)).toInt()


    if (diffDays<1){
        val dateFormat = SimpleDateFormat("H:mm")
        return dateFormat.format(date)
    }
    else if (diffDays<7){
        val dateFormat = SimpleDateFormat("EE")
        return dateFormat.format(date)
    }
    else if (date.year == now.year){
        val dateFormat = SimpleDateFormat("d.MMM")
        return dateFormat.format(date)
    }
    else {
        val dateFormat = SimpleDateFormat("MMM yyyy")
        return dateFormat.format(date)
    }

}