package com.example.antinorms

import com.example.antinorms.models.LoginReq
import com.example.antinorms.models.LoginResponse

import com.example.antinorms.models.projectResp.ProjectResponse
import com.example.antinorms.models.project_update.pro_update
import com.example.antinorms.models.updateProjectModel.req.res.UpdateProjectReq
import com.example.antinorms.models.updateProjectModel.res.UpdateProjecRes
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET("developer/list")
    fun getData(@Header("Authorization") token:String): Call<MyDataClassForApi>

    @PUT("developer/updateProfile/{devId}")
    fun updateDeveloper(@Path("devId") devId: String,  @Body saveDevrequest: SaveDevrequest): Call<SaveDevRes>

    @GET("project/api/v1/projects/viewall")
    fun getData2(@Header("Authorization") token:String): Call<ProjectResponse>

    @POST("/auth/login")
    fun login(@Body loginReq: LoginReq):Call<LoginResponse>

//    @GET("role/getAllRoles")
//    fun getData3(@Header("Authorization") token: String): Call<roledata>




  @PUT("project/api/v1/projects/update")
  fun updateProjects(@Body updateProjectReq: UpdateProjectReq) : Call<UpdateProjecRes>

  @GET("/project/api/v1/projects/viewall/")
  fun getData3(): Call<pro_update>

}