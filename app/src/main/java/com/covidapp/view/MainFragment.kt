package com.covidapp.view

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.covidapp.R
import com.covidapp.databinding.FragmentMainBinding
import com.covidapp.util.Constants.Companion.DATE
import com.covidapp.util.Constants.Companion.TOTAL_CASES_BY_DATE
import com.covidapp.util.Constants.Companion.TOTAL_CASES_TILL_TODAY
import com.covidapp.util.Constants.Companion.TYPE
import java.util.*


class MainFragment : Fragment(R.layout.fragment_main) {

    private var fragmentMainBinding: FragmentMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)
        fragmentMainBinding = binding
        clickListeners()

    }

    override fun onDestroyView() {
        fragmentMainBinding = null
        super.onDestroyView()
    }

    private fun clickListeners() {

        fragmentMainBinding?.btnTotalCaseTillToday?.setOnClickListener {

            val bundle = Bundle()
            bundle.putString(TYPE, TOTAL_CASES_TILL_TODAY)
            findNavController().navigate(R.id.action_mainFragment_to_reportFragment, bundle)

        }

        fragmentMainBinding?.btnTotalCaseTillDate?.setOnClickListener {

            val cldr: Calendar = Calendar.getInstance()
            val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
            val month: Int = cldr.get(Calendar.MONTH)
            val year: Int = cldr.get(Calendar.YEAR)
            val divider = "-"
            val datepicker = DatePickerDialog(
                (activity as AppCompatActivity),
                OnDateSetListener { view, year1, monthOfYear, dayOfMonth ->
                    val monthString :String
                    val monthInt: Int = monthOfYear+1
                    if (monthInt < 10) {
                        monthString = "0".plus(monthOfYear)
                    } else {
                        monthString = monthInt.toString()
                    }
                    val dayString:String = if (dayOfMonth < 10) {
                        "0".plus(dayOfMonth)
                    } else {
                        dayOfMonth.toString()
                    }

                    val date = "$year1$divider$monthString$divider$dayString"
                    val bundle = Bundle()
                    bundle.putString(TYPE, TOTAL_CASES_BY_DATE)
                    bundle.putString(DATE, date)
                    findNavController().navigate(R.id.action_mainFragment_to_reportFragment, bundle)
                },
                year,
                month,
                day
            )
            datepicker.show()


        }
        fragmentMainBinding?.btnCountries?.setOnClickListener {

            findNavController().navigate(R.id.action_mainFragment_to_countryFragment)
        }

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

    }

}