package com.covidapp.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.covidapp.services.model.TotalCases
import com.covidapp.services.repository.DataRepository


class CovidReportViewModel(var dataRepository: DataRepository) : ViewModel() {

    private lateinit var totalCaseMutableLiveData: MutableLiveData<List<TotalCases>>
    private lateinit var dailyReportCaseMutableLiveData: MutableLiveData<List<TotalCases>>

   // var dataRepository: DataRepository = dataRepository

    fun getLatestTotalsCases(): MutableLiveData<List<TotalCases>> {
        totalCaseMutableLiveData = dataRepository.getLatestTotalsCases()
        return totalCaseMutableLiveData

    }

    fun getDailyReportTotalsByDate(date: String): MutableLiveData<List<TotalCases>> {
        dailyReportCaseMutableLiveData = dataRepository.getDailyReportTotalsByDate(date)
        return dailyReportCaseMutableLiveData
    }


}