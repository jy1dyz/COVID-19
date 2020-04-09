package kg.study.covid.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApi {

    @GET("countries")
    fun getCountries(): Single<List<Country>>

    @GET("countries/{countryName}")
    fun getCountry(@Path("countryName") countryName: String): Single<Country>
}