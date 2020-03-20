package kg.study.covid.model_global

import com.google.gson.annotations.SerializedName

data class GlobalData(
    @SerializedName("cases")
    val cases: String?,
    @SerializedName("deaths")
    val deaths: String?,
    @SerializedName("recovered")
    val recovered: String?
)