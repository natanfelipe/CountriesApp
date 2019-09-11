package com.natanbrito.countriesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natanbrito.countriesapp.api.CountriesApi
import com.natanbrito.countriesapp.di.ApiServiceModule
import com.natanbrito.countriesapp.di.DaggerApiServiceComponent
import com.natanbrito.countriesapp.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CountryViewModel : ViewModel() {

    @Inject
    lateinit var countriesApi: CountriesApi

    val countries by lazy { MutableLiveData<List<Country>>() }
    val isLoading by lazy { MutableLiveData<Boolean>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    private val compositeDisposable = CompositeDisposable()

    init {
        DaggerApiServiceComponent.builder()
                .apiServiceModule(ApiServiceModule())
                .build()
                .inject(this)
    }

    fun refresh() {
        isLoading.value = true
        getAllCountries()
    }

    private fun getAllCountries() {
        compositeDisposable.addAll(
                countriesApi.getAllCountries()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                            override fun onSuccess(list: List<Country>) {
                                isLoading.value = false
                                loadError.value = false
                                countries.value = list
                            }

                            override fun onError(e: Throwable) {
                                e.printStackTrace()
                                isLoading.value = false
                                loadError.value = true
                                countries.value = null
                            }

                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}