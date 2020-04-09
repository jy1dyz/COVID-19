package kg.study.covid.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.util.Util
import kg.study.covid.R
import kg.study.covid.model.Country
import kg.study.covid.util.getProgressDrawable
import kg.study.covid.util.transliteration
import kotlinx.android.synthetic.main.item_country.view.*

import kotlin.collections.ArrayList

class CountryListAdapter(var countries: ArrayList<Country>): RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries:List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    fun updateCountry(country: Country) {
        countries.clear()
        countries.add(country)
        notifyDataSetChanged()
    }
    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val countryName = view.tv_country
        private val cases = view.tv_cases
        private val todayCases = view.tv_today
        private val death = view.tv_deaths
        private val todayDeath = view.tv_today_two
        private val recovered = view.tv_recovered
        private val active = view.tv_active
        private val critical = view.tv_critical
        private val totalTests = view.tv_total_tests
        private val progressDrawable = getProgressDrawable(view.context)
        //private val rusCountryName = transliteration("$countryName" )


        fun bind(country: Country) {
            countryName.text = country.countryName
            cases.text = country.cases.plus(" cases")
            todayCases.text = country.todayCases.plus(" today")
            death.text = country.deaths.plus(" deaths")
            todayDeath.text =  country.todayDeaths.plus(" deaths today")
            recovered.text = country.recovered.plus(" recovered")
            active.text = country.active.plus(" active")
            critical.text = country.critical.plus(" critical")
            totalTests.text = country.totalTests.plus(" total tests")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun getItemCount(): Int = countries.size



    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        //val countryTask: Country = filterCountries[position]
        holder.bind(countries[position])
    }


}