package kg.study.covid.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import kg.study.covid.CustomItemClickListener
import kg.study.covid.R
import kg.study.covid.model.Country
import kg.study.covid.util.getProgressDrawable
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(var countries: ArrayList<Country>): RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

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

   /** override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    filteredCountriesList = countries
                } else {
                    val filteredList: ArrayList<Country> = ArrayList()
                    for (row in countries) {

                        //change this to filter according to your case
                        if (row.countryName?.toLowerCase()!!.contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    filteredCountriesList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredCountriesList
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                filteredCountriesList = filterResults.values as ArrayList<Country>
                notifyDataSetChanged()
            }
        }

    } */

    companion object {
        fun updateCountries(countryListAdapter: CountryListAdapter, newCountries:List<Country>) {
            countryListAdapter.countries.clear()
            countryListAdapter.countries.addAll(newCountries)
            countryListAdapter.notifyDataSetChanged()
        }
    }


}