package com.gutani.componentspt1

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import java.util.*
import kotlin.collections.ArrayList

class CitySearchAdapter (ctx:Context, layout:Int, private val fullList:List<String>) : ArrayAdapter<String>(ctx, layout, fullList), Filterable {
    private var results:List<String>
    private val cityFilter:Filter

    init {
        this.results = fullList
        this.cityFilter = CityFilter()
    }

    override fun getCount(): Int = results.size

    override fun getItem(position: Int): String? = if(results.isNotEmpty() && position < results.size) results[position] else null

    override fun getFilter(): Filter = cityFilter

    inner class CityFilter() :Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResults = FilterResults()
            var temp:List<String> = ArrayList()
            if(constraint != null) {
                val term = constraint.toString().trim().toLowerCase(Locale.ROOT)
                temp = fullList.filter {
                    it.removeAccents().indexOf(term) > -1
                }
            }
            filterResults.values = temp
            filterResults.count = temp.size
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, innerResults: FilterResults?) {
            results = innerResults?.values as List<String>
            notifyDataSetChanged()
        }
    }
}