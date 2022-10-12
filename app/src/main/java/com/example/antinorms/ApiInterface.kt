package com.example.antinorms

import com.example.antinorms.models.LoginReq
import com.example.antinorms.models.LoginResponse
import com.example.antinorms.models.Register.registerdata
import com.example.antinorms.models.createproject.createProject
import com.example.antinorms.models.createproject.createresp

import com.example.antinorms.models.projectResp.ProjectResponse
import com.example.antinorms.models.project_update.pro_update
import com.example.antinorms.models.registerresp
import com.example.antinorms.models.updateProjectModel.req.res.UpdateProjectReq
import com.example.antinorms.models.updateProjectModel.res.UpdateProjecRes
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET("developer/list")
    fun getData(@Header("Authorization") token: String): Call<MyDataClassForApi>


    @PUT("developer/updateProfile/{devId}")
    fun updateDeveloper(
        @Header("Authorization") token: String,
        @Path("devId") devId: String,
        @Body saveDevrequest: SaveDevrequest
    ): Call<SaveDevRes>

    @GET("project/api/v1/projects/viewall")
    fun getData2(@Header("Authorization") token: String): Call<ProjectResponse>

    @POST("/auth/login")
    fun login(@Body loginReq: LoginReq): Call<LoginResponse>

//    @GET("role/getAllRoles")
//    fun getData3(@Header("Authorization") token: String): Call<roledata>


    @PUT("project/api/v1/projects/update")
    fun updateProjects(
        @Header("Authorization") token: String,
        @Body updateProjectReq: UpdateProjectReq): Call<UpdateProjecRes>


    @POST("/developer/register")
    fun getData3(
        @Header("Authorization") s: String,
        @Body registerdata: registerdata
    ): Call<registerresp>


  @POST("/projects/add")
  fun getData4(
      @Header("Authorization") s: String,
      @Body createProject: createProject
  ):Call<createresp>


}