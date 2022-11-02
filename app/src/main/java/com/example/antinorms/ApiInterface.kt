package com.example.antinorms

import com.example.antinorms.models.*
import com.example.antinorms.models.Register.registerdata
import com.example.antinorms.models.createproject.createProject
import com.example.antinorms.models.createproject.createresp
import com.example.antinorms.models.createteam.*
import com.example.antinorms.models.getdesignation.getdesignationresp

import com.example.antinorms.models.projectResp.ProjectResponse

import com.example.antinorms.models.teckstack.techresp
import com.example.antinorms.models.updateProjectModel.req.res.UpdateProjectReq
import com.example.antinorms.models.updateProjectModel.res.UpdateProjecRes
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("developer/list")
    fun getData( @Header("Authorization") token: String): Call<MyDataClassForApi>

    @GET("/designation/getAllDesignation")
    fun getdesignation(@Header("Authorization") token: String): Call<getdesignationresp>

    @GET("/techStack/getAll")
    fun gettech(@Header("Authorization") token: String): Call<techresp>

    @GET("/group/get-groupData/")
    fun getgroup(@Header("Authorization")token: String): Call<groupdata>


    @GET("/role/getAllRoles")
    fun getrole(@Header("Authorization")token: String): Call<role>


    @POST("teams/add-member")
    fun addTeam(
        @Header("Authorization") token: String,
        @Body addTeamRequest: AddTeamRequest): Call<AddTeamResponse>


    @POST("/group/create-group")
    fun addgroup(
        @Header("Authorization") token: String,
        @Body addGroupRequest: groupRequest): Call<GroupAdd>






    @GET("/teams/get-data?page=2")
    fun getData5(@Header("Authorization") token: String): Call<response_teams>

    @GET("/group/get-groupData")
    fun getgroupdata(@Header("Authorization") token: String): Call<addgroup>




    @PUT("developer/updateProfile/{devId}")
    fun updateDeveloper(
        @Header("Authorization") token: String,
        @Path("devId") devId: String,
        @Body saveDevrequest: SaveDevrequest
    ): Call<SaveDevRes>

    @GET("project/api/v1/projects/viewall")
    fun getData2(@Header("Authorization") token: String): Call<ProjectResponse>

    @GET("/group/get-groupData")
    fun getData6(@Header("Authorization") token: String): Call<group_res>

    @POST("/auth/login")
    fun login(

        @Body loginReq: LoginReq): Call<LoginResponse>

//    @GET("role/getAllRoles")
//    fun getData3(@Header("Authorization") token: String): Call<roledata>


    @PUT("project/api/v1/projects/update")
    fun updateProjects(
        @Header("Authorization") token: String,
        @Body updateProjectReq: UpdateProjectReq
    ): Call<UpdateProjecRes>


    @POST("/developer/register")
    fun getData3(
        @Header("Authorization") s: String,
        @Body registerdata: registerdata
    ): Call<registerresp>


  @POST("/project/api/v1/projects/add")
  fun getData4(
      @Header("Authorization") s: String,
      @Body createProject: createProject
  ):Call<createresp>


}