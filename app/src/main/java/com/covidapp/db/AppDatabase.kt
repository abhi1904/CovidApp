package com.covidapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.covidapp.services.model.Country


@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val countryDBDao: CountryDBDao

}
