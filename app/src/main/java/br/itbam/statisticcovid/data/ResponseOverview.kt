package br.itbam.statisticcovid.data

import com.google.gson.annotations.SerializedName

class ResponseOverview(
    @SerializedName("Global")
    val global: GlobalSummary,

    @SerializedName("Countries")
    val countries: ArrayList<Country>
)
