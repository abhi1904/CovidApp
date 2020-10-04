package com.covidapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.covidapp.R
import com.covidapp.databinding.FragmentCountryBinding
import com.covidapp.services.model.Country
import com.covidapp.viewmodel.CountryCasesViewModel
import com.covidapp.viewmodel.CountryCasesViewModelFactory

class CountryFragment : Fragment(R.layout.fragment_country) {
    private var fragmentCountryBinding: FragmentCountryBinding? = null
    private lateinit var countryCasesViewModel: CountryCasesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCountryBinding.bind(view)
        fragmentCountryBinding = binding


        var countryCasesViewModelFactory = CountryCasesViewModelFactory()
        countryCasesViewModel = ViewModelProvider(
            this,
            countryCasesViewModelFactory
        ).get(CountryCasesViewModel::class.java)

        fragmentCountryBinding?.progressCircular?.visibility = View.VISIBLE
        countryCasesViewModel.getAllCountriesFromDB().observe(viewLifecycleOwner, Observer { countries ->
            if (!countries.isNullOrEmpty()) {

                fragmentCountryBinding?.rvCountry?.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = CountryListAdapter(activity, countries)

                }

            }
            else
            {
                countryCasesViewModel.getAllCountries()
                    .observe(viewLifecycleOwner, Observer { countries ->
                        if (!countries.isNullOrEmpty()) {

                            fragmentCountryBinding?.rvCountry?.apply {
                                layoutManager = LinearLayoutManager(activity)
                                adapter = CountryListAdapter(activity, countries)

                            }

                        }
                    })
            }
            fragmentCountryBinding?.progressCircular?.visibility = View.GONE
        })

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.countryList)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() { // Handle the back button event

                    NavHostFragment.findNavController(this@CountryFragment).popBackStack()

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

    }

    override fun onDestroyView() {
        fragmentCountryBinding = null
        super.onDestroyView()
    }


}