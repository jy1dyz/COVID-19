package kg.study.covid.model_global

import io.reactivex.Single
import kg.study.covid.model.CountriesApi
import kg.study.covid.model.Country
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GlobalService {
    private val BASE_URL = "https://coronavirus-19-api.herokuapp.com"
    private val api: GlobalApi

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GlobalApi::class.java)
    }

    fun getCountries(): Single<GlobalData> {
        return api.getAll()
    }
}