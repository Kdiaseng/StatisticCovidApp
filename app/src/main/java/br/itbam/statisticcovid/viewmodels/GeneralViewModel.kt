package br.itbam.statisticcovid.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.itbam.statisticcovid.api.repository.CovidRepository
import br.itbam.statisticcovid.data.Country
import br.itbam.statisticcovid.data.GlobalSummary
import br.itbam.statisticcovid.data.ResponseOverview
import kotlinx.coroutines.launch

class GeneralViewModel : ViewModel() {

    private val repository = CovidRepository()

    private val _dataLiveData: MutableLiveData<ResponseOverview> = MutableLiveData()
    val dataLiveData: LiveData<ResponseOverview> = _dataLiveData

    private val _globalResultLiveData: MutableLiveData<GlobalSummary> = MutableLiveData()
    val  globalResultLiveData: LiveData<GlobalSummary> = _globalResultLiveData

    private val _countriesLiveData: MutableLiveData<ArrayList<Country>> = MutableLiveData()
    val countriesLiveData: LiveData<ArrayList<Country>> = _countriesLiveData

    fun getStatisticDataCovidWorld(){
        viewModelScope.launch {
            val resultData = repository.getSummary()
            resultData?.let {
                _dataLiveData.value = it
                setGlobalResult(it)
                setCountries(it)
            }
        }
    }

    fun getCountryByCode(code: String): Country?{
        return _countriesLiveData.value?.find {country ->
            country.countryCode == code
        }
    }

    private fun setCountries(responseOverview: ResponseOverview) {
        _globalResultLiveData.value = responseOverview.global
    }

    private fun setGlobalResult(responseOverview: ResponseOverview) {
        _countriesLiveData.value = responseOverview.countries
    }


}