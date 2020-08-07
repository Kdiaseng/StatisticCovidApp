package br.itbam.statisticcovid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.itbam.statisticcovid.data.Country
import br.itbam.statisticcovid.databinding.ItemCountryBinding
import java.util.*
import kotlin.collections.ArrayList

class CountryAdapter(
    private val countries: ArrayList<Country>,
    val onItemClick: (country: Country) -> Unit,
    val onClickStar: (country: Country) -> Unit
) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>(), Filterable {
    private var countryFilterList = ArrayList<Country>()

    init {
        countryFilterList = countries
    }

    private var index: Int = -1

    class ViewHolder(val itemCountryBinding: ItemCountryBinding) :
        RecyclerView.ViewHolder(itemCountryBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = countryFilterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countryFilterList[position]
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                if (charSearch.isEmpty()) {
                    countryFilterList = countries
                } else {
                    val resultList = ArrayList<Country>()
                    for (row in countries) {
                        if (row.country.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                        countryFilterList = resultList
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<Country>
                notifyDataSetChanged()
            }

        }
    }
}