package com.natanbrito.countriesapp.di

import com.natanbrito.countriesapp.api.ApiService
import com.natanbrito.countriesapp.viewmodel.CountryViewModel
import dagger.Component

@Component(modules = [ApiServiceModule::class])
interface ApiServiceComponent {

    fun inject(apiService: ApiService)
    fun inject(countryViewModel: CountryViewModel)

}