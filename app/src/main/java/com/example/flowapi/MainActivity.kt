package com.example.flowapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
    //asFlow builder ->  To Convert list of data into Flow
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = listOf("vraj", "Riti", "Bruno", "Zayn", "Ranbir", "dualipa", "Niha").asFlow()
            .flowOn(Dispatchers.IO) //process background ma karava

        runBlocking {
            data.collect {
                Log.d("VRAJTEST", "onCreate: $it")
            }
        }
    }
}