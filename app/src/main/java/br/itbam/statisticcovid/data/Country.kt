package br.itbam.statisticcovid.data

import com.google.gson.annotations.SerializedName

class Country (
    @SerializedName("Country")
    val country: String,

    @SerializedName("CountryCode")
    val countryCode: String,

    @SerializedName("Slug")
    val slug: String,

    @SerializedName("NewConfirmed")
    val newConfirmed: Long,

    @SerializedName("TotalConfirmed")
    val totalConfirmed: Long,

    @SerializedName("NewDeaths")
    val newDeaths: Long,

    @SerializedName("TotalDeaths")
    val totalDeaths: Long,

    @SerializedName("NewRecovered")
    val newRecovered: Long,

    @SerializedName("TotalRecovered")
    val totalRecovered: Long,

    var isSelected: Boolean

)

