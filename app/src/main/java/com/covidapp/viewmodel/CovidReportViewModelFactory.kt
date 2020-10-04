package com.covidapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.covidapp.CovidApplication
import com.covidapp.di.APIComponent
import com.covidapp.services.repository.DataRepository
import javax.inject.Inject

class CovidReportViewModelFactory : ViewModelProvider.Factory {
    lateinit var apiComponent: APIComponent
    @Inject
    lateinit var dataRepository: DataRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        apiComponent = CovidApplication.apiComponent
        apiComponent.inject(this)
        if (modelClass.isAssignableFrom(CovidReportViewModel::class.java)) {
            return CovidReportViewModel(dataRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

