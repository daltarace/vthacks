package com.example.vthacks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.vthacks.databinding.SingleItemBinding
import java.util.*
import kotlin.collections.ArrayList
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create

class RvAdapter(
    var languageList: List<Language>,
) : RecyclerView.Adapter<RvAdapter.ViewHolder>(), Filterable {

    var favoriteList: MutableList<Language> = mutableListOf<Language>()

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
                binding.activityGpa.text = "Average Gpa: " + this.gpa
                binding.activityWiki.text = this.wiki
                binding.activityMode.text = this.mode
                binding.activitySemester.text = this.semester
                binding.activityExtra.text = this.extra
                if(this.isfav)
                {
                    binding.activityFavoritebutton.setBackgroundResource(R.drawable.ic_heart_solid)
                }
                else
                {
                    binding.activityFavoritebutton.setBackgroundResource(R.drawable.ic_heart_regular)
                }

                binding.activityFavoritebutton.setOnClickListener{
                    if(this.isfav == false){
                        this.isfav = true
                        favoriteList.add(this)
                        binding.activityFavoritebutton.setBackgroundResource(R.drawable.ic_heart_solid)
                        Log.d("fav", "from adapter " +favoriteList.toString())
                    }
                    else {
                        this.isfav = false;
                        favoriteList.remove(this)
                        binding.activityFavoritebutton.setBackgroundResource(R.drawable.ic_heart_regular)
                        Log.d("fav", "from adapter " +favoriteList.toString())
                    }
                }
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
                if(MainActivity.isFavOn){
                    languageFilterList = favoriteList as ArrayList<Language>
                    val filterResults = FilterResults()
                    filterResults.values = languageFilterList //here
                    return filterResults
                }
                val charSearch = constraint.toString()
                val courseApi = RetrofitHelper.getInstance().create(CourseApi::class.java)
                // launching a new coroutine
                val resultList = ArrayList<Language>()
                GlobalScope.launch {
                    val result = courseApi.getCourses("[\""+charSearch+"\"]", 202201)
                    if (result != null)
                        for (course in result.body()!!){
                            resultList.add(Language(course.name, course.subject + " " + course.courseNumber, course.instructor, "3.5",  "You learn how to program mobile apps in Java and Kotlin. Assignments are usually project based with a few quizzes. No final exam or midterm. ", "course.modality", "Spring/Fall", "Project Based"))
                        }
                }
                languageFilterList = resultList
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
