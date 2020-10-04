package com.covidapp.di


import com.covidapp.db.LocalCacheManager
import com.covidapp.services.repository.DataRepository
import com.covidapp.viewmodel.CountryCasesViewModel
import com.covidapp.viewmodel.CountryCasesViewModelFactory
import com.covidapp.viewmodel.CovidReportViewModel
import com.covidapp.viewmodel.CovidReportViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface APIComponent {

    fun inject(countryCasesViewModel: CountryCasesViewModel)
    fun inject(countryCasesViewModel: CovidReportViewModel)
    fun inject(covidReportViewModelFactory: CovidReportViewModelFactory)
    fun inject(countryCasesViewModelFactory: CountryCasesViewModelFactory)
    fun inject(dataRepository: DataRepository)
    fun inject(localCacheManager: LocalCacheManager)

}



