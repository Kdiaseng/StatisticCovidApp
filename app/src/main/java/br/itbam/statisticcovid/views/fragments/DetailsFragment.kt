package br.itbam.statisticcovid.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.itbam.statisticcovid.R
import br.itbam.statisticcovid.databinding.FragmentDetailsBinding
import br.itbam.statisticcovid.utils.SharedPreferenceUtils
import br.itbam.statisticcovid.viewmodels.GeneralViewModel

class DetailsFragment : Fragment() {
    private lateinit var detailsBinding: FragmentDetailsBinding
    private  lateinit var viewModel : GeneralViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(GeneralViewModel::class.java)
        detailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)
         return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val country = DetailsFragmentArgs.fromBundle(it).country
            country?.let {
                detailsBinding.countryDetail = country
            }?:kotlin.run {
                getCountryBySharedPreference()
            }
        }
    }

    private fun getCountryBySharedPreference() {
        val preferenceString =
            SharedPreferenceUtils(requireActivity()).getPreferenceString(getString(R.string.code_country))
        preferenceString?.let {
             detailsBinding.countryDetail = viewModel.getCountryByCode(it)
        }
    }

}