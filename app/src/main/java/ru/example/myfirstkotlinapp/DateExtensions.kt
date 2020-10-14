package ru.example.myfirstkotlinapp

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun Date.removeTime(): Date {
    val cal = Calendar.getInstance() // get calendar instance
    cal.time=this
//    val  sdf =  SimpleDateFormat("dd/MM/yyyy")
    cal[Calendar.DAY_OF_MONTH] = 1
    cal[Calendar.HOUR_OF_DAY] = 0 // set hour to midnight
    cal[Calendar.MINUTE] = 0 // set minute in hour
    cal[Calendar.SECOND] = 0 // set second in minute
    cal[Calendar.MILLISECOND] = 0 // set millisecond in second

    return cal.time
//    sdf.format(

//    )// return the date part

}

//fun Date.time123(): Date{
//
//    val c1 = Calendar.getInstance()
//
//    c1[Calendar.DAY_OF_MONTH] = 1
//    cal[Calendar.HOUR_OF_DAY] = 0 // set hour to midnight
//    cal[Calendar.MINUTE] = 0 // set minute in hour
//    cal[Calendar.SECOND] = 0 // set second in minute
//    cal[Calendar.MILLISECOND] = 0 // set millisecond in second
//
//    println("${c1.time} + ${c2.time} = ${cTotal.time}")
//
//
//    return cal.time
////    sdf.format(
//
////    )// return the date part
//    }

