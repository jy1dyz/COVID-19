package kg.study.covid.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.study.covid.R
import kg.study.covid.ui.countries.CountriesViewModel
import kg.study.covid.view.CountryListAdapter
import kg.study.covid.view.KyrgyzstanAdapter
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.android.synthetic.main.fragment_countries.list_error
import kotlinx.android.synthetic.main.fragment_countries.loading_view

class AboutFragment : Fragment() {

    lateinit var aboutViewModel: AboutViewModel
    private val countriesAdapter = KyrgyzstanAdapter(arrayListOf())
    // private val kyrgyzstanAdapter = KyrgyzstanAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutViewModel =
            ViewModelProviders.of(this).get(AboutViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_about, container, false)

        val countryKyrgyzstan : RecyclerView = root.findViewById(R.id.kyrgyzstan)
        countryKyrgyzstan.setHasFixedSize(true)
        countryKyrgyzstan.layoutManager = LinearLayoutManager(activity)
        val adapter = countriesAdapter
        countryKyrgyzstan.adapter = adapter

        aboutViewModel.refresh()

        getCountryPath()

        return root
    }

    fun getCountryPath() {
        aboutViewModel.countryPath.observe(viewLifecycleOwner, Observer {
                countryPath ->
            countryPath?.let {
                kyrgyzstan.visibility = View.VISIBLE
                countriesAdapter.updateCountry(it) }
        })

        aboutViewModel.countryLoadError.observe(viewLifecycleOwner, Observer {
                isError ->
            isError?.let {list_error.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        aboutViewModel.loading.observe(viewLifecycleOwner, Observer {
                isLoading ->
            isLoading?.let { loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    list_error.visibility = View.GONE
                    kyrgyzstan.visibility = View.GONE
                }}
        })
    }
}
