package br.itbam.statisticcovid.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.itbam.statisticcovid.databinding.FragmentOverViewBinding
import br.itbam.statisticcovid.viewmodels.GeneralViewModel


class OverViewFragment : Fragment() {

    private lateinit var generalViewModel: GeneralViewModel
    private lateinit var dataBinding: FragmentOverViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentOverViewBinding.inflate(inflater, container, false)
        generalViewModel = ViewModelProvider(requireActivity()).get(GeneralViewModel::class.java)
        dataBinding.viewmodel = generalViewModel
        dataBinding.lifecycleOwner = this
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        generalViewModel.getStatisticDataCovidWorld()
    }
}