package kg.study.covid.model

import android.util.Log
import io.reactivex.Single
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

class CountriesService {

    private val TAG = "CountriesService"
    private val BASE_URL = "https://coronavirus-19-api.herokuapp.com"
    private val api: CountriesApi
    private val cacheSize = 10 * 1024 * 1024

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesApi::class.java)

    }

    fun getCountries():Single<List<Country>> {
        return api.getCountries()
    }

    fun getCountry():Single<Country> {
        return api.getCountry("Kyrgyzstan")
    }

}