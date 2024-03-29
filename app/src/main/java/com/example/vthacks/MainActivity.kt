package com.example.vthacks

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vthacks.databinding.ActivityMainBinding
import java.io.*


class MainActivity : AppCompatActivity() {
    // view binding for the activity
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    companion object{
        var courseDescMap : HashMap<String, String> = HashMap<String, String> ()
        var isFavOn = false
    }

    // create reference to the adapter and the list
    // in the list pass the model of Language
    private lateinit var rvAdapter: RvAdapter
    private lateinit var languageList : List<Language>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("Chautest", "hi")

        val minput = InputStreamReader(assets.open("sample_data.csv"))
        val reader = BufferedReader(minput)
        var line: String
        reader.forEachLine {
            val rowByComma = "$it".split(",").toTypedArray()
            val rowByQuotation = "$it".split("\"").toTypedArray()
            val profName = rowByComma[3].split(" ")
            val profNameLast = profName[profName.size - 1]
            if(rowByQuotation.size >=2){
                courseDescMap.put(rowByComma[1] + " "+ profNameLast, rowByQuotation[1])
            }
            else
            {
                courseDescMap.put(rowByComma[1] + " "+ profNameLast, "Description Not Found")
            }
//            Log.d("Chautestdesc", rowByComma[1] + " "+ profNameLast)
        }


        // load data to language list
        loadLanguage()

        // create  layoutManager
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)

        // pass it to rvLists layoutManager
        binding.rvList.setLayoutManager(layoutManager)

        // initialize the adapter,
        // and pass the required argument
        rvAdapter = RvAdapter(languageList)

        // attach adapter to the recycler view
        binding.rvList.adapter = rvAdapter

        binding.firstsearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.firstsearchView.setVisibility(View.GONE)
                binding.startingtext.setVisibility(View.GONE)
                binding.allfavoritebutton.setVisibility(View.VISIBLE)
                binding.rvList.setVisibility(View.VISIBLE)
                binding.searchView2.setVisibility(View.VISIBLE)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                rvAdapter.filter.filter(newText)
                return false
            }

        })

        binding.allfavoritebutton.setOnClickListener {
            //Toast.makeText(this@MainActivity, rvAdapter.favoriteList.toString(), Toast.LENGTH_SHORT).show()
            //rvAdapter.languageFilterList = rvAdapter.favoriteList as ArrayList<Language>
            isFavOn = !isFavOn
            if(isFavOn) {
                binding.allfavoritebutton.setBackgroundResource(R.drawable.ic_heart_solid)
                Log.d("fav", "from main " +rvAdapter.favoriteList.toString())
            }
            else if(!isFavOn){
                binding.allfavoritebutton.setBackgroundResource(R.drawable.ic_heart_regular)
                Log.d("fav", "from main " +rvAdapter.favoriteList.toString())
            }
            if(rvAdapter.favoriteList.size == 0 ) { //don't crash if its null
                rvAdapter.filter.filter("")
            }
            else
            {
                rvAdapter.filter.filter(rvAdapter.favoriteList[0].classname)

            }
            //rvAdapter.filter.filter(rvAdapter.favoriteList[1].classname)
        }

        binding.searchView2.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                rvAdapter.filter.filter(newText)

                return false
            }

        })



    }
    // add items to the list manually in our case
    private fun loadLanguage() {
        languageList = listOf(
            Language("Computer Systems","CS 3214","Godmar Back", "3.3","All of it is in C. Process management with fork/join. ", "In-Person", "Spring/Fall", "Coding Heavy"),
            Language("Data Algo","CS 4104","Sally Hamouda", "3.1","Lot's of algorithms are taught.  ", "Hybrid", "Spring Only","Theory"),
            Language("Mobile","CS 3714","Andrey Esakia", "3.7","You learn how to program mobile apps in Java and Kotlin. Assignments are usually project based with a few quizzes. No final exam or midterm. ","Online Synchonous", "Spring/Fall", "Project Based"),

            /*Language("Kotlin" , "Exp : 2 years"),
            Language("Python" , "Exp : 4 years"),
            Language("JavaScript" , "Exp : 6 years"),
            Language("PHP" , "Exp : 1 years"),
            Language("CPP" , "Exp : 8 years"),*/
        )



    }
    // on destroy of view make
    // the binding reference to null
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}