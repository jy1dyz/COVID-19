package kg.study.covid.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.study.covid.R
import kg.study.covid.model.Country
import kg.study.covid.util.getProgressDrawable
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(var countries: ArrayList<Country>): RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries:List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
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
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country) {
            countryName.text = country.countryName
            cases.text = country.cases.plus(" cases")
            todayCases.text = country.todayCases.plus(" today")
            death.text = country.deaths.plus(" deaths")
            todayDeath.text =  country.todayDeaths.plus(" deaths today")
            recovered.text = country.recovered.plus(" recovered")
            active.text = country.active.plus(" active")
            critical.text = country.critical.plus(" critical")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun getItemCount(): Int = countries.size



    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }
}