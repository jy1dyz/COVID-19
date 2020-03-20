package kg.study.covid.model

import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {

    @GET("countries")
    fun getCountries(): Single<List<Country>>
}