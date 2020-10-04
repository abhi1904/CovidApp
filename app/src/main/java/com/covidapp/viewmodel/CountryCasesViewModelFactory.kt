package com.covidapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.covidapp.CovidApplication
import com.covidapp.di.APIComponent
import com.covidapp.services.repository.DataRepository
import javax.inject.Inject

class CountryCasesViewModelFactory : ViewModelProvider.Factory {
    lateinit var apiComponent: APIComponent

    @Inject
    lateinit var dataRepository: DataRepository


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var apiComponent: APIComponent = CovidApplication.apiComponent
        apiComponent.inject(this)
        if (modelClass.isAssignableFrom(CountryCasesViewModel::class.java)) {
            return CountryCasesViewModel(dataRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

