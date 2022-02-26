package com.example.vthacks

data class Course (
    val subject: String,
    val capacity: Int,
    val modality: String,
    val courseNumber: String,
    val credits: Int,
    val term:Int,
    val name : String,
    val instructor: String,
    val exam: String,
    val crn: Int,
    val type: String
    // meetings are ignored
)