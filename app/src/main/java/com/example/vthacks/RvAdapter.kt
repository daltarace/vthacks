package com.example.vthacks

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.vthacks.databinding.SingleItemBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


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
//                binding.activityWiki.text = this.wiki
                binding.activityWiki.setText(this.wiki)
                binding.activityWiki.isFocusable = false
//                binding.activityWiki.isEnabled = true
                binding.activityWiki.setOnLongClickListener {
                    binding.activityWiki.isFocusableInTouchMode = true
                    return@setOnLongClickListener true
                }

                binding.activityWiki.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        val teacher = binding.activityTeacher.text.split(" ")
                        val teacherLast = teacher[teacher.size-1]
                        MainActivity.courseDescMap.set(binding.activityClasscode.text as String +" "+teacherLast,s.toString())
//                        Toast.makeText(application, classCode+activityTeacher, Toast.LENGTH_LONG)
//                        Log.d("Chautestedit", binding.activityClasscode.text as String + " " +teacherLast)
                    }
                })
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
//                        Log.d("fav", "from adapter " +favoriteList.toString())
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

                    for (course in languageFilterList){
                         val activityTeacher = course.teacher.split(" ")
                        val activityTeacherLast = activityTeacher[activityTeacher.size-1]
                        val courseNameProf:String = course.classcode +" "+ activityTeacherLast
//                            Log.d("Chautestnameprof",courseNameProf)
                        val courseDesc:String? = MainActivity.courseDescMap.get(courseNameProf)
                        if (courseDesc != null) {
                            course.wiki = courseDesc
                        }
                    }
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
                            val activityTeacher = course.instructor.split(" ")
                            val activityTeacherLast = activityTeacher[activityTeacher.size-1]
                            val courseNameProf:String = course.subject + " " + course.courseNumber +" "+ activityTeacherLast
//                            Log.d("Chautestnameprof",courseNameProf)
                            val courseDesc:String? = MainActivity.courseDescMap.get(courseNameProf)

                            if (courseDesc != null) {
                            resultList.add(Language(course.name, course.subject + " " + course.courseNumber,
                                course.instructor, ((26..40).random().toDouble()/10).toString(),
                                courseDesc, course.modality?.toString() ?: "Unknown", "Spring/Fall", "Project Based"))
                            }
                            else
                            {
                                resultList.add(Language(course.name, course.subject + " " + course.courseNumber,
                                    course.instructor, ((26..40).random().toDouble()/10).toString(),
                                    "Content not found", course.modality?.toString() ?: "Unknown", "Spring/Fall", "Project Based"))
                            }
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
