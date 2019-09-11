package com.natanbrito.countriesapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.natanbrito.countriesapp.databinding.CountryItemLayoutBinding
import com.natanbrito.countriesapp.interfaces.OnClickListener
import com.natanbrito.countriesapp.model.Country
import com.natanbrito.countriesapp.view.fragments.ListFragmentDirections
import com.natanbrito.countriesapp.viewholder.CountryViewHolder

class CountryAdapter(private val countryList: ArrayList<Country>) :
        RecyclerView.Adapter<CountryViewHolder>(), OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = CountryItemLayoutBinding.inflate(inflater, parent, false)

        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int = countryList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.click = this
    }

    fun update(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)

        notifyDataSetChanged()
    }

    override fun onClick(view: View) {
        for (country in countryList) {
            if (view.tag == country.name) {
                val action = ListFragmentDirections.actionGoToCountry(country)
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

}