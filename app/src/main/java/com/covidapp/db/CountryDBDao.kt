package com.covidapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.covidapp.services.model.Country
import io.reactivex.Maybe

@Dao
interface CountryDBDao {

    @Query("SELECT * FROM Countries")
    fun getCountries(): Maybe<List<Country>>

    @Insert()
    fun saveCountries(countries: List<Country>)
}
