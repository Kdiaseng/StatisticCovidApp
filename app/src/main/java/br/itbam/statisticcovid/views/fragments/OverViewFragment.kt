package br.itbam.statisticcovid.views.fragments

import android.content.Intent.getIntent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.itbam.statisticcovid.R
import br.itbam.statisticcovid.databinding.FragmentOverViewBinding
import br.itbam.statisticcovid.utils.LocateHelper
import br.itbam.statisticcovid.utils.SharedPreferenceUtils
import br.itbam.statisticcovid.viewmodels.GeneralViewModel


class OverViewFragment : Fragment() {

    private lateinit var generalViewModel: GeneralViewModel
    private lateinit var dataBinding: FragmentOverViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkLocaleInPreference()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.icLanguage -> reloadActivity("br")
            R.id.icLanguageBr -> reloadActivity("")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveLanguage(locale: String = "") {
        SharedPreferenceUtils(requireActivity()).saveStringInPreference(
            getString(R.string.code_language),
            locale
        )
    }

    private fun reloadActivity(locale: String) {
        saveLanguage(locale)
        LocateHelper.setAppLocale(requireActivity(), locale )
        activity?.finish()
        activity?.startActivity(activity?.intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        generalViewModel = ViewModelProvider(requireActivity()).get(GeneralViewModel::class.java)
        dataBinding = FragmentOverViewBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@OverViewFragment
            viewmodel = generalViewModel
        }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun checkLocaleInPreference() {
        val locale =
            SharedPreferenceUtils(requireActivity()).getPreferenceString(getString(R.string.code_language))
        LocateHelper.setAppLocale(requireActivity(), locale?:"")

    }

    private fun loadData() {
        generalViewModel.getStatisticDataCovidWorld()
    }
}