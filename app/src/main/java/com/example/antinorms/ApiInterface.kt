package com.example.antinorms

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET("developer/list")
    fun getData(): Call<MyDataClassForApi>

    @PUT("developer/updateProfile/{devId}")
    fun updateDeveloper(@Path("devId") devId: String,  @Body saveDevrequest: SaveDevrequest): Call<SaveDevRes>

    @GET("project/api/v1/projects/viewall")
    fun getData2(): Call<projectprofile>



//  @PUT("project/api/v1/projects/update")
//  fun updateProjects()

//  @GET("/project/api/v1/projects/update")
//  fun getData3(): Call<projectupdates>

}