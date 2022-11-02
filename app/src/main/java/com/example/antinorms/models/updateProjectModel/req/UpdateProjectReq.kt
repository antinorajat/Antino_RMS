package com.example.antinorms.models.updateProjectModel.req.res

import com.example.antinorms.models.updateProjectModel.req.UpdateData
import com.google.gson.annotations.SerializedName

data class UpdateProjectReq(
    @SerializedName("id"         ) var id         : String?     = null,
    @SerializedName("updateData" ) var updateData : UpdateData? = UpdateData()
)
