package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val listOfTasks = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Let's detect when the user clicks on the add button
        findViewById<Button>(R.id.button).setOnClickListener{
            // Code in here is going to be execute d when the user clicks on a button
            Log.i("caren", "clicked on... ")
        }

        listOfTasks.add("Do laundry")
        listOfTasks.add("Go for a walk")

        // Lookup the recyclerview in activity layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView

        // Create adapter passing in the sample user data
        val adapter = TaskItemAdapter(listOfTasks)

        // Attach the adapter to the recyclerview to populate items
        recyclerView.adapter = adapter

        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}