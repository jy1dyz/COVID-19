package kg.study.covid.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.study.covid.R
import kg.study.covid.model.Country
import kg.study.covid.model_global.GlobalData
import kg.study.covid.util.getProgressDrawable
import kotlinx.android.synthetic.main.item_country.view.*
import kotlinx.android.synthetic.main.item_global.view.*
import java.util.logging.Logger.global

class GlobalListAdapter(var global: ArrayList<GlobalData>): RecyclerView.Adapter<GlobalListAdapter.GlobalViewHolder>() {

    fun updateGlobal(newCountries:List<GlobalData>) {
        global.clear()
        global.addAll(newCountries)
        notifyDataSetChanged()
    }
    class GlobalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cases = view.tv_global
        private val deaths = view.tv_cases_global
        private val recovered = view.tv_amount
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(global: GlobalData) {
            cases.text = global.cases.plus(" - Global Coronavirus cases")
            deaths.text = global.deaths.plus(" - Global Deaths")
            recovered.text = global.recovered.plus(" - Global Recovered")
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        GlobalListAdapter.GlobalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_global, parent, false)
        )

    override fun getItemCount(): Int = global.size

    override fun onBindViewHolder(holder: GlobalViewHolder, position: Int) {
        holder.bind(global[position])
    }
}

