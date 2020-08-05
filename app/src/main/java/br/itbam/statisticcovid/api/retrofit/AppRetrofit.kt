package br.itbam.statisticcovid.api.retrofit

import br.itbam.statisticcovid.api.service.CovidService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AppRetrofit {

    private val BASE_URL: String = "https://api.covid19api.com"

    private val client by lazy{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .build()
    }



    private val retrofit by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(this.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    val covidService: CovidService by lazy {
        retrofit.create(CovidService::class.java)
    }


}