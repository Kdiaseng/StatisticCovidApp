package br.itbam.statisticcovid.api.repository

import br.itbam.statisticcovid.api.retrofit.AppRetrofit
import br.itbam.statisticcovid.api.service.CovidService

class CovidRepository(private val covidService: CovidService = AppRetrofit().covidService) {

    private val TAG = "COVID_REPOSITORY"

    suspend fun getSummary() =
        try {
            val response = covidService.getSummary()
            if (response.isSuccessful)
                response.body()
            else
                null
        } catch (ex: Exception) {
            null
        }


}
