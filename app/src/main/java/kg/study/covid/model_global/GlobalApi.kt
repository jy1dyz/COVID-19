package kg.study.covid.model_global

import io.reactivex.Single
import kg.study.covid.model.Country
import retrofit2.http.GET

interface GlobalApi {

    @GET("all")
    fun getAll(): Single<GlobalData>
}