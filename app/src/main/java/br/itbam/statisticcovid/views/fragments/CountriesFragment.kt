package br.itbam.statisticcovid.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.itbam.statisticcovid.adapters.CountryAdapter
import br.itbam.statisticcovid.viewmodels.GeneralViewModel
import br.itbam.statisticcovid.databinding.FragmentCountriesBinding
import kotlinx.android.synthetic.main.fragment_countries.*

class CountriesFragment : Fragment() {
    private lateinit var generalViewModel: GeneralViewModel

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
        loadRecyclerView()

    }

    private fun loadRecyclerView() {
        generalViewModel.countriesLiveData.observe(viewLifecycleOwner, Observer {
            rvCountries.adapter = CountryAdapter(it,{
                Toast.makeText(requireContext(), "Country ${it.country}", Toast.LENGTH_SHORT).show()
            },{
                Toast.makeText(requireContext(), "STAR", Toast.LENGTH_SHORT).show()
                Log.e("star", it.isSelected.toString())
            })
            rvCountries.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        })
    }

}