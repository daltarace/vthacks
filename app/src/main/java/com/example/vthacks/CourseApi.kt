package com.example.vthacks

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseApi {
    // hardcoded query, may be changed -- idk how to do input query
    @GET("/beta/courses/search")
    suspend fun getCourses(@Query("query") search:String?,@Query("term") term:Int) : Response<List<Course>>
}