package kg.study.covid.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("country")
    val countryName: String?,

    @SerializedName("cases")
    val cases: String?,

    @SerializedName("todayCases")
    val todayCases: String?,

    @SerializedName("deaths")
    val deaths: String?,

    @SerializedName("todayDeaths")
    val todayDeaths: String?,

    @SerializedName("recovered")
    val recovered: String?,

    @SerializedName("active")
    val active: String?,

    @SerializedName("critical")
    val critical: String?,

    @SerializedName("totalTests")
    val totalTests: String?

)