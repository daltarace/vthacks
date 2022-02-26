package com.example.vthacks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vthacks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // view binding for the activity
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    // create reference to the adapter and the list
    // in the list pass the model of Language
    private lateinit var rvAdapter: RvAdapter
    private lateinit var languageList : List<Language>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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