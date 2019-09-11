package com.natanbrito.countriesapp.utils

import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.natanbrito.countriesapp.R
import com.natanbrito.countriesapp.model.Country

@BindingAdapter("property", "type")
fun getProperties(view: AppCompatTextView, country: Country, type: String){
    view.text = when(type){
        view.context.getString(R.string.lb_languages) ->
            String.format(type,country.languages?.joinToString { languages ->
                "${languages.name}"})
        view.context.getString(R.string.lb_currency) ->
            String.format(type,country.currencies?.joinToString { currencies ->
                "${currencies.name} (${currencies.code})"})
        view.context.getString(R.string.lb_borders) -> String.format(type,country.borders?.joinToString { borders ->
            borders
        })
        else -> "oi"
    }

    Log.d("TESTE",view.text.toString())
    Log.d("TESTE", String.format(type,country.borders?.joinToString { borders ->
        borders
    }))
}