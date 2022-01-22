package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    var listOfTasks = mutableListOf<String>()
    lateinit var adapter:TaskItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : TaskItemAdapter.OnLongClickListener{
            override fun onItemLongClicked(position: Int) {
                // 1. Remove the item from the list
                listOfTasks.removeAt(position)
                // 2. Notify the adapter that our data set has changed
                adapter.notifyDataSetChanged()
                saveItems()
            }

        }

        // 1. Let's detect when the user clicks on the add button
//        findViewById<Button>(R.id.button).setOnClickListener{
//            // Code in here is going to be execute d when the user clicks on a button
//            Log.i("caren", "clicked on... ")
//        }

        loadItems()

        // Lookup the recyclerview in activity layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView

        // Create adapter passing in the sample user data
        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)

        // Attach the adapter to the recyclerview to populate items
        recyclerView.adapter = adapter

        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)


        val inputTextField = findViewById<EditText>(R.id.addTaskField)

        // Set up the button and input field, so that the user can end a task and add it to the list
        findViewById<Button>(R.id.button).setOnClickListener{
            // 1. Grab the text the user had inputted into @id/addTaskField
            val userInputtedTask = inputTextField.text.toString();

            // 2. Add the string ot our list of tasks: listOfTasks
            listOfTasks.add(userInputtedTask)

            // notify the adapter that has been updated
            adapter.notifyItemInserted(listOfTasks.size - 1)

            // 3. Reset text field
            inputTextField.setText("")
            saveItems()
        }
    }

    // Save the data the user has inputted
    // by reading from and writing to a file

    // Create a method to get the file we need
    fun getDataFile(): File {

        // Every line is going to represent a specific task in our list of tasks
            return File(filesDir, "data.txt")
    }

    // load the items by reading every line in the data file
    fun loadItems(){
        try{
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset() )

        }catch(ioException:IOException){
            ioException.printStackTrace()
        }
    }


    // save items by writing them into our data file
    fun saveItems(){
        try{
            FileUtils.writeLines(getDataFile(), listOfTasks)
        }catch(ioException:IOException){
            ioException.printStackTrace()
        }

    }

}