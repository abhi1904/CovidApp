package com.covidapp.db

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import com.covidapp.CovidApplication
import com.covidapp.di.APIComponent
import com.covidapp.services.model.Country
import com.covidapp.util.Constants.Companion.DB_NAME
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class LocalCacheManager(context: Context) {
    private val countryDBDao: CountryDBDao =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .allowMainThreadQueries()
            .build().countryDBDao

    fun saveCountries(countries: List<Country>, responseCallback: ResponseCallback) {
        Completable.fromAction { countryDBDao.saveCountries(countries) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {}

                override fun onComplete() {
                    responseCallback.onSaveCountries()
                }

                override fun onError(e: Throwable) {
                    responseCallback.onError()
                }
            })
    }

    @SuppressLint("CheckResult")
    fun getCountries(responseCallback: ResponseCallback) {
        countryDBDao.getCountries().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe { countries ->
                responseCallback.onGetCountry(countries)
            }
    }



}
