package kg.study.covid.ui.countries

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.study.covid.R
import kg.study.covid.view.CountryListAdapter
import kg.study.covid.view.CovidActivity
import kotlinx.android.synthetic.main.fragment_countries.*

class CountriesFragment : Fragment() {

    lateinit var countriesViewModel: CountriesViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        countriesViewModel =
            ViewModelProviders.of(this).get(CountriesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_countries, container, false)
        val countriesList : RecyclerView = root.findViewById(R.id.countriesList)
        countriesList.setHasFixedSize(true)
        countriesList.layoutManager = LinearLayoutManager(activity)
        val adapter = countriesAdapter
        countriesList.adapter = adapter

        countriesViewModel.refresh()

        observeViewModel()

        return root
    }

    fun observeViewModel() {
        countriesViewModel.countries.observe(this, Observer {
                countries ->
            countries?.let {
                countriesList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it) }
        })

        countriesViewModel.countryLoadError.observe(this, Observer {
                isError ->
            isError?.let {list_error.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        countriesViewModel.loading.observe(this, Observer {
                isLoading ->
            isLoading?.let { loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    list_error.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }}
        })
    }
}
