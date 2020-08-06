package br.itbam.statisticcovid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.itbam.statisticcovid.R
import br.itbam.statisticcovid.data.Country
import br.itbam.statisticcovid.databinding.ItemCountryBinding
import kotlinx.android.synthetic.main.item_country.view.*


class CountryAdapter(private val countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView) {
        fun bindView(country: Country) {
           itemView.tvNameCountry.text = country.country
           itemView.tvCodeCountry.text = country.countryCode
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.bindView(country)
    }
}