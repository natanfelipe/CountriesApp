package com.natanbrito.countriesapp.api

import com.natanbrito.countriesapp.di.ApiServiceModule
import com.natanbrito.countriesapp.di.DaggerApiServiceComponent
import javax.inject.Inject

class ApiService {

    @Inject
    lateinit var api: CountriesApi


    init {
        DaggerApiServiceComponent
                .builder()
                .apiServiceModule(ApiServiceModule())
                .build()
    }

    fun getAllCountries() = api.getAllCountries()

}