package com.example.vthacks

// this is the Language model class
class Language(
    val classname: String = "",
    val classcode: String = "",
    val teacher: String = "",
    val gpa: String = "",
    val wiki: String = "",

    //these are for the chips
    val mode: String = "",
    val semester: String = "",
    val extra: String = "",

    //if its favorited or not
    var isfav: Boolean = false
)