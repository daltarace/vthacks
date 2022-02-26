package com.example.vthacks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.vthacks.databinding.SingleItemBinding
import java.util.*
import kotlin.collections.ArrayList

class RvAdapter(
    var languageList: List<Language>,
) : RecyclerView.Adapter<RvAdapter.ViewHolder>(), Filterable {

    var languageFilterList = ArrayList<Language>()
    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root)
    init {
        languageFilterList = ArrayList<Language>(languageList)
    }

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    // bind the items with each item
    // of the list languageList
    // which than will be
    // shown in recycler view
    // to keep it simple we are
    // not setting any image data to view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(languageFilterList[position]){

                //new below
                binding.activityClassname.text = this.classname
                binding.activityClasscode.text = this.classcode
                binding.activityTeacher.text = this.teacher
                binding.activityGpa.text = this.gpa
                binding.activityWiki.text = this.wiki
                binding.activityMode.text = this.mode
                binding.activitySemester.text = this.semester
                binding.activityExtra.text = this.extra
            }
        }
    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return languageFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    languageFilterList = ArrayList<Language>(languageList)
                } else {
                    val resultList = ArrayList<Language>()
                    for (row in languageList) {
                        if (row.classcode.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT)) || row.classname.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    languageFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = languageFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                languageFilterList = results?.values as ArrayList<Language>
                notifyDataSetChanged()
            }

        }
    }
}
