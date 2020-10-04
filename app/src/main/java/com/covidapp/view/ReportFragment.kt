package com.covidapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.covidapp.R
import com.covidapp.databinding.FragmentReportBinding
import com.covidapp.util.Constants
import com.covidapp.viewmodel.CovidReportViewModel
import com.covidapp.viewmodel.CovidReportViewModelFactory
import com.google.gson.Gson


class ReportFragment : Fragment(R.layout.fragment_report) {
    private var fragmentReportBinding: FragmentReportBinding? = null
    private lateinit var covidReportViewModel: CovidReportViewModel
    private var type: String = ""
    private var date: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentReportBinding.bind(view)
        fragmentReportBinding = binding

        val covidReportViewModelFactory = CovidReportViewModelFactory()
        covidReportViewModel = ViewModelProvider(
            this,
            covidReportViewModelFactory
        ).get(CovidReportViewModel::class.java)

        fragmentReportBinding?.progressCircular?.visibility = View.VISIBLE
        type = requireArguments().getString(Constants.TYPE).toString()
        if (type.equals(Constants.TOTAL_CASES_BY_DATE)) {
            date = requireArguments().getString(Constants.DATE).toString()
            covidReportViewModel.getDailyReportTotalsByDate(date)
                .observe(viewLifecycleOwner, Observer { totalCase ->
                    if (totalCase != null && totalCase.isNotEmpty()) {
                        fragmentReportBinding?.tvReports?.text = Gson().toJson(totalCase)
                    } else {
                        fragmentReportBinding?.tvReports?.text = getString(R.string.noResultFound)
                    }
                    fragmentReportBinding?.progressCircular?.visibility = View.GONE

                })
        } else {
            covidReportViewModel.getLatestTotalsCases()
                .observe(viewLifecycleOwner, Observer { totalCase ->
                    if (totalCase != null && totalCase.isNotEmpty()) {
                        fragmentReportBinding?.tvReports?.text = Gson().toJson(totalCase)
                    } else {
                        fragmentReportBinding?.tvReports?.text = getString(R.string.noResultFound)
                    }
                    fragmentReportBinding?.progressCircular?.visibility = View.GONE

                })

        }

        (activity as AppCompatActivity).supportActionBar?.title = type.plus(date)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() { // Handle the back button event

                    NavHostFragment.findNavController(this@ReportFragment).popBackStack()

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

    }

    override fun onDestroyView() {
        fragmentReportBinding = null
        super.onDestroyView()
    }


}