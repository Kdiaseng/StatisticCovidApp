package br.itbam.statisticcovid.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.itbam.statisticcovid.R
import br.itbam.statisticcovid.adapters.CountryAdapter
import br.itbam.statisticcovid.data.Country
import br.itbam.statisticcovid.databinding.FragmentCountriesBinding
import br.itbam.statisticcovid.utils.SharedPreferenceUtils
import br.itbam.statisticcovid.viewmodels.GeneralViewModel
import kotlinx.android.synthetic.main.fragment_countries.*

class CountriesFragment : Fragment() {
    private lateinit var generalViewModel: GeneralViewModel
    private  var adapter: CountryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentCountriesBinding.inflate(
            inflater,
            container,
            false
        )
        generalViewModel = ViewModelProvider(requireActivity()).get(GeneralViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        country_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filter?.filter(newText)
                return false
            }

        })

        loadRecyclerView()

    }

    private fun loadRecyclerView() {
        generalViewModel.countriesLiveData.observe(viewLifecycleOwner, Observer {
           adapter = CountryAdapter(it, { country ->
               val action =
                   CountriesFragmentDirections.actionCountriesFragmentToDetailsFragment(country)
               rvCountries.findNavController().navigate(action)
           }, {
               saveCountryInSharedPreference(it)
           })
            rvCountries.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            rvCountries.adapter = adapter
        })
    }

    private fun saveCountryInSharedPreference(country: Country) {
        SharedPreferenceUtils(requireActivity()).saveStringInPreference(
            getString(R.string.code_country),
            country.countryCode
        )
    }

    override fun onResume() {
        super.onResume()
        country_search.setQuery("", true)
    }

}