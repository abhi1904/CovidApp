package com.covidapp.services.repository

import androidx.lifecycle.MutableLiveData
import com.covidapp.CovidApplication
import com.covidapp.db.LocalCacheManager
import com.covidapp.db.ResponseCallback
import com.covidapp.di.APIComponent
import com.covidapp.services.DataApi
import com.covidapp.services.model.Country
import com.covidapp.services.model.TotalCases
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class DataRepository {
    var apiComponent: APIComponent = CovidApplication.apiComponent

    @Inject
    lateinit var localCacheManager: LocalCacheManager

    @Inject
    lateinit var retrofit: Retrofit
    var dataApiService: DataApi


    init {
        apiComponent.inject(this)
        dataApiService = retrofit.create(DataApi::class.java)

    }

    fun getAllCountries(): MutableLiveData<List<Country>> {
        val data = MutableLiveData<List<Country>>()
        dataApiService.getAllCountries()
            ?.enqueue(object : Callback<List<Country>> {
                override fun onResponse(
                    call: Call<List<Country>?>,
                    response: Response<List<Country>>
                ) {
                    response.body()?.let { saveCountries(it) }
                    data.postValue(response.body())
                }

                override fun onFailure(
                    call: Call<List<Country>>,
                    t: Throwable
                ) {
                    data.postValue(null)
                }
            })
        return data
    }


    fun getLatestTotalsCases(): MutableLiveData<List<TotalCases>> {
        val data = MutableLiveData<List<TotalCases>>()
        dataApiService.getLatestTotalsCases()
            ?.enqueue(object : Callback<List<TotalCases>> {
                override fun onResponse(
                    call: Call<List<TotalCases>>,
                    response: Response<List<TotalCases>>
                ) {
                    data.postValue(response.body())
                }

                override fun onFailure(
                    call: Call<List<TotalCases>>,
                    t: Throwable
                ) {
                    data.postValue(null)
                }
            })
        return data
    }

    fun getDailyReportTotalsByDate(date: String?): MutableLiveData<List<TotalCases>> {
        val data = MutableLiveData<List<TotalCases>>()

        dataApiService.getDailyReportTotalsByDate(date)
            ?.enqueue(object : Callback<List<TotalCases>> {
                override fun onResponse(
                    call: Call<List<TotalCases>>,
                    response: Response<List<TotalCases>>
                ) {
                    data.postValue(response.body())
                }

                override fun onFailure(
                    call: Call<List<TotalCases>>,
                    t: Throwable
                ) {
                    data.postValue(null)
                }
            })
        return data
    }


    fun getListOfCountriesFromDB(): MutableLiveData<List<Country>> {
        val countriesMutableLD = MutableLiveData<List<Country>>()
        localCacheManager
            .getCountries(object : ResponseCallback {
                override fun onError() {}
                override fun onSaveCountries() {
                }

                override fun onGetCountry(countries: List<Country>) {

                    if (countries.isNotEmpty()) {
                        countriesMutableLD.postValue(countries)
                    } else {
                        countriesMutableLD.postValue(null)
                    }
                }
            })
        return countriesMutableLD
    }

    fun saveCountries(countries: List<Country>): MutableLiveData<Boolean> {
        val tMutableLiveData = MutableLiveData<Boolean>()
        localCacheManager
            .saveCountries(countries, object : ResponseCallback {
                override fun onGetCountry(countries: List<Country>) {
                }

                override fun onSaveCountries() {
                    tMutableLiveData.value = true

                }

                override fun onError() {
                    tMutableLiveData.value = false

                }


            })
        return tMutableLiveData
    }



}