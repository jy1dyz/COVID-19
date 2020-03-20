package kg.study.covid.ui.global

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
import kg.study.covid.view.CountryListAdapter
import kg.study.covid.view.GlobalListAdapter
import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.android.synthetic.main.fragment_countries.list_error
import kotlinx.android.synthetic.main.fragment_countries.loading_view
import kotlinx.android.synthetic.main.fragment_global.*

class GlobalFragment : Fragment() {

    private lateinit var globalViewModel: GlobalViewModel
    private val globalAdapter = GlobalListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        globalViewModel =
            ViewModelProviders.of(this).get(GlobalViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_global, container, false)

        val globalList : RecyclerView = root.findViewById(R.id.global_list)
        globalList.setHasFixedSize(true)
        globalList.layoutManager = LinearLayoutManager(activity)
        val adapter = globalAdapter
        globalList.adapter = adapter

        globalViewModel.refresh()

        observeViewModel()

        return root
    }

    private fun observeViewModel() {
        globalViewModel.global.observe(this, Observer {
                global ->
            global?.let {
                global_list.visibility = View.VISIBLE
                globalAdapter.updateGlobal(it) }
        })

        globalViewModel.countryLoadError.observe(this, Observer {
                isError ->
            isError?.let {list_error.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        globalViewModel.loading.observe(this, Observer {
                isLoading ->
            isLoading?.let { loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    list_error.visibility = View.GONE
                    global_list.visibility = View.GONE
                }}
        })
    }
}
