package com.example.sharedpreference

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity




class MainActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonRetrieve: Button
    private lateinit var textViewResult: TextView

    // Key for SharedPreferences
    private val PREF_NAME = "MyPrefs"
    private val Key_name = "Username"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        editTextUsername = findViewById(R.id.editTextUsername)
        buttonSave = findViewById(R.id.buttonSave)
        buttonRetrieve = findViewById(R.id.buttonRetrieve)
        textViewResult = findViewById(R.id.textViewResult)

        buttonSave.setOnClickListener {
            val name = editTextUsername.text.toString()
            if (name.isNotEmpty()) {
                saveNameToPreference(name)
            }else{
                Toast.makeText(this, "plz enter your name",Toast.LENGTH_SHORT).show()
            }
        }
        buttonRetrieve.setOnClickListener {
            val savedName = retrieveNameFromPreference()
            if (savedName.isNotEmpty()){
                textViewResult.text = "Retrived Name:$savedName"
            }else{
                textViewResult.text= "No name saved yet!"
            }
        }

    }

    private fun saveNameToPreference(name: String) {
        val sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(Key_name, name)
        editor.apply()
        Toast.makeText(this,"Name saved successfully ",Toast.LENGTH_SHORT).show()
    }

    private fun retrieveNameFromPreference():String {
        val sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(Key_name, "")?:""

    }


}