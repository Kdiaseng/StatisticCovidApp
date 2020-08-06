package br.itbam.statisticcovid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.itbam.statisticcovid.data.Country
import br.itbam.statisticcovid.databinding.ItemCountryBinding

class CountryAdapter(
    private val countries: ArrayList<Country>,
    val onItemClick: (country: Country) -> Unit,
    val onClickStar: (country: Country) -> Unit
) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    private var index: Int = -1

    class ViewHolder(val itemCountryBinding: ItemCountryBinding) :
        RecyclerView.ViewHolder(itemCountryBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.itemCountryBinding.item = country

        holder.itemCountryBinding.setShowDetails {
            onItemClick(country)
        }

        holder.itemCountryBinding.setSetFavorite {
            index = position
            notifyDataSetChanged()
            onClickStar(country)
        }
        country.isSelected = index == position

    }
}