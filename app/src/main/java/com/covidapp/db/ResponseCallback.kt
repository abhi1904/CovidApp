package com.covidapp.db

import com.covidapp.services.model.Country

interface ResponseCallback {

    fun onGetCountry(countries: List<Country>)
    fun onSaveCountries()
    fun onError()
}
