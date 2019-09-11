package com.natanbrito.countriesapp.di

import com.natanbrito.countriesapp.BuildConfig
import com.natanbrito.countriesapp.api.ApiService
import com.natanbrito.countriesapp.api.CountriesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiServiceModule {


    @Provides
    fun provideRetrofit() = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesApi::class.java)

    @Provides
    fun provideApiService() = ApiService()

}