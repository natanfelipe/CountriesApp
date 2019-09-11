package com.natanbrito.countriesapp.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.natanbrito.countriesapp.R
import com.natanbrito.countriesapp.databinding.FragmentCountryBinding
import com.natanbrito.countriesapp.model.Country

class CountryFragment : Fragment() {

    private lateinit var binding: FragmentCountryBinding
    private lateinit var country: Country

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            country = CountryFragmentArgs.fromBundle(it).country
        }

        binding.country = country
    }
}
