package com.covidapp.services

import com.covidapp.services.model.Country
import com.covidapp.services.model.TotalCases
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DataApi {
    @GET("help/countries?format=json")
    fun getAllCountries(
    ): Call<List<Country>>?

    @GET("totals?format=json")
    fun getLatestTotalsCases(
    ): Call<List<TotalCases>>?

    @GET("report/totals?date-format=YYYY-MM-DD&format=json")
    fun getDailyReportTotalsByDate(
        @Query("date") date: String?
    ): Call<List<TotalCases>>?


}