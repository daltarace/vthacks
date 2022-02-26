package com.example.vthacks

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseApi {
    // hardcoded query, may be changed -- idk how to do input query
    @GET("/beta/courses/search?query=[\"cs3114\"]&size=1000&term=202201")
    suspend fun getCourses() : Response<List<Course>>
}