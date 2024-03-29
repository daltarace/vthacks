package com.example.vthacks

// this is the Language model class
class Language(
    val classname: String = "", //name
    val classcode: String = "", //subject + courseNumber
    val teacher: String = "", //instructor
    val gpa: String = "",
    var wiki: String = "",

    //these are for the chips
    val mode: String = "",
    val semester: String = "",
    val extra: String = "",

    //if its favorited or not
    var isfav: Boolean = false
)