package com.covidapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.covidapp.services.model.Country
import com.covidapp.services.repository.DataRepository


class CountryCasesViewModel(dataRepository: DataRepository) : ViewModel() {
    private lateinit var countryLiveData: MutableLiveData<List<Country>>
    private lateinit var countryFromDBLiveData: MutableLiveData<List<Country>>

    private var dataRepository: DataRepository = dataRepository

    fun getAllCountries(): MutableLiveData<List<Country>> {
        countryLiveData = dataRepository.getAllCountries()
        return countryLiveData

    }

    fun getAllCountriesFromDB(): MutableLiveData<List<Country>> {
        countryFromDBLiveData = dataRepository.getListOfCountriesFromDB()
        return countryFromDBLiveData

    }

    
}