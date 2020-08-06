package br.itbam.statisticcovid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.itbam.statisticcovid.data.Country
import br.itbam.statisticcovid.databinding.ItemCountryBinding

class CountryAdapter(private val countries: ArrayList<Country>,val onItemClick: (country: Country) -> Unit) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemCountryBinding: ItemCountryBinding) :
        RecyclerView.ViewHolder(itemCountryBinding.root) {
        fun bindView(country: Country) {
            itemCountryBinding.item = country
            itemCountryBinding.setShowDetails {
                onItemClick(country)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.bindView(country)
    }
}