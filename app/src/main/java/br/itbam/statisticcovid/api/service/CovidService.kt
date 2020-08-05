package br.itbam.statisticcovid.api.service

import br.itbam.statisticcovid.data.ResponseOverview
import retrofit2.Response
import retrofit2.http.GET


interface CovidService {
    @GET("summary")
    suspend fun getSummary(): Response<ResponseOverview>

}