package com.natanbrito.countriesapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(val capital: String?,
                   val area: Float?,
                   val name:String?,
                   @SerializedName("flagPNG")
                   val flag: String?,
                   val subregion: String?,
                   val translations: Translation?,
                   val currencies: List<Currency>?,
                   val languages: List<Languages>?,
                   val borders: List<String>?): Parcelable

@Parcelize
data class Translation(val br: String?,
                       val de: String?,
                       val es: String?,
                       val fa: String?,
                       val fr: String?,
                       val hr: String?,
                       val it: String?): Parcelable

@Parcelize
data class Languages(val name: String?): Parcelable

@Parcelize
data class Currency(val name: String?,
                    val symbol: String?,
                    val code: String?): Parcelable