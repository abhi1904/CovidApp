package com.covidapp.services.model

import com.google.gson.annotations.SerializedName
data class TotalCases(
    @SerializedName("confirmed") val confirmed: Int,
    @SerializedName("recovered") val recovered: Int,
    @SerializedName("critical") val critical: Int,
    @SerializedName("deaths") val deaths: Int,
    @SerializedName("lastChange") val lastChange: String,
    @SerializedName("lastUpdate") val lastUpdate: String

)