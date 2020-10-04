package com.covidapp.di

import com.covidapp.CovidApplication
import dagger.Module
import dagger.Provides

// for context object
@Module
class AppModule constructor(covidApplication: CovidApplication){

    var covidApplication: CovidApplication = covidApplication

    @Provides
    fun provideMyApplication(): CovidApplication {
        return covidApplication
    }
}