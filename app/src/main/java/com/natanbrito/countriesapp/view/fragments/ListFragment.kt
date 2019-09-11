package com.natanbrito.countriesapp.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.natanbrito.countriesapp.R
import com.natanbrito.countriesapp.model.Country
import com.natanbrito.countriesapp.view.adapters.CountryAdapter
import com.natanbrito.countriesapp.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var countryViewModel: CountryViewModel
    private var countriesList = CountryAdapter(arrayListOf())


    private val listCountriesObserver: Observer<List<Country>> = Observer {list ->
        list?.let {
            rv_country.visibility = View.VISIBLE
            countriesList.update(it)
        }
    }

    private val loadingObserver: Observer<Boolean> = Observer { isLoading ->
        progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
        if(isLoading){
            rv_country.visibility = View.GONE
            tv_error.visibility = View.GONE
        }
    }

    private val errorObserver: Observer<Boolean> = Observer { isError ->
        if (isError){
            tv_error.visibility = View.VISIBLE
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)

        countryViewModel.countries.observe(this, listCountriesObserver)
        countryViewModel.isLoading.observe(this, loadingObserver)
        countryViewModel.loadError.observe(this, errorObserver)
        countryViewModel.refresh()

        rv_country.apply {
            adapter = countriesList
        }

        srl.setOnRefreshListener {
            rv_country.visibility = View.GONE
            tv_error.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            countryViewModel.refresh()
            srl.isRefreshing = false
        }

    }



}
