package kg.study.covid.ui.countries

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.study.covid.ItemOffsetDecoration
import kg.study.covid.R
import kg.study.covid.view.CountryListAdapter
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

        //val searchBar = root.findViewById(R.id.search_bar) as SearchView
        //searchBar.add

        /**val countryPath: RecyclerView = root.findViewById(R.id.kyrgyzstan)
        countryPath.setHasFixedSize(true)
        countryPath.layoutManager = LinearLayoutManager(activity)
        val adapterKyrgyzstan = kyrgyzstanAdapter
        countryPath.adapter = adapterKyrgyzstan */

        val countriesList : RecyclerView = root.findViewById(R.id.countriesList)
        countriesList.setHasFixedSize(true)
        countriesList.layoutManager = LinearLayoutManager(activity)
        countriesList.addItemDecoration(ItemOffsetDecoration(10))
        val adapter = countriesAdapter
        countriesList.adapter = adapter
        countriesList.scrollToPosition(adapter.itemCount - 1)

        countriesViewModel.refresh()
        observeViewModel()
       // getCountryPath()

        return root
    }



    fun observeViewModel() {
        countriesViewModel.countries.observe(viewLifecycleOwner, Observer {
                countries ->
            countries?.let {
                countriesList.visibility = View.VISIBLE
                CountryListAdapter.updateCountries(countriesAdapter, it) }
        })

        countriesViewModel.countryLoadError.observe(viewLifecycleOwner, Observer {
                isError ->
            isError?.let {list_error.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        countriesViewModel.loading.observe(viewLifecycleOwner, Observer {
                isLoading ->
            isLoading?.let { loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    list_error.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }}
        })
    }


    fun getCountryPath() {
        countriesViewModel.countryPath.observe(viewLifecycleOwner, Observer {
                countryPath ->
            countryPath?.let {
                countriesList.visibility = View.VISIBLE
                countriesAdapter.updateCountry(it) }
        })

        countriesViewModel.countryLoadError.observe(viewLifecycleOwner, Observer {
                isError ->
            isError?.let {list_error.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        countriesViewModel.loading.observe(viewLifecycleOwner, Observer {
                isLoading ->
            isLoading?.let { loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    list_error.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }}
        })
    }


   /** override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        var searchView: SearchView? = null
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        searchView!!.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                countriesAdapter.filter.filter(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
    } */

}
