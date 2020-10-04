package com.covidapp

import android.app.Application
import android.content.Context
import com.covidapp.di.APIComponent
import com.covidapp.di.ApiModule
import com.covidapp.di.AppModule
import com.covidapp.di.DaggerAPIComponent
import com.covidapp.util.Constants.Companion.BASE_URL


class CovidApplication : Application() {


    companion object {
        var ctx: Context? = null
        lateinit var apiComponent: APIComponent
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        apiComponent = initDaggerComponent()

    }

    fun getMyComponent(): APIComponent {
        return apiComponent
    }

    fun initDaggerComponent(): APIComponent {
        apiComponent = DaggerAPIComponent.builder()
            .apiModule(ApiModule(BASE_URL)).appModule(
                AppModule(
                    this
                )
            ).build()

        return apiComponent

    }
}