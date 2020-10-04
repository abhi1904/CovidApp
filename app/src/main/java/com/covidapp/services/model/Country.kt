package com.covidapp.services.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Countries")
data class Country(
    @PrimaryKey @SerializedName("name") val name: String,
    @SerializedName("alpha2code") val alpha2code: String?,
    @SerializedName("alpha3code") val alpha3code: String?,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?


)