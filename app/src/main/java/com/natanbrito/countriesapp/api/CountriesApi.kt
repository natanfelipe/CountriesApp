package com.natanbrito.countriesapp.api

import com.natanbrito.countriesapp.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {

    @GET("countriesV2.json")
    fun getAllCountries(): Single<List<Country>>

}