package com.example.vthacks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vthacks.databinding.SingleItemBinding

class RvAdapter(
    var languageList: List<Language>,
) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root)

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
            with(languageList[position]){

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
        return languageList.size
    }
}
