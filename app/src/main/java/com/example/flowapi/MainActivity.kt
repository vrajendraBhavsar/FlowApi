package com.example.flowapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
    //When to use Map ?
/*
*  - To make any changes/update/mapping which Flow is emitting, we can use Map for that
*  - Actual output jode chhedchhad kari, desired output display karva
* */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var data = flowOf("Vraj", "Riti", "Bruno", "Zayn", "ranbir", "dualipa", "Niha")
            .flowOn(Dispatchers.IO) //process background ma karava

        runBlocking {
            data
                .map { name ->
                    if (name.startsWith("R") || name.startsWith("N")) {
                        "Miss $name"
                    } else {
                        "Mr $name"
                    }
                }
                .collect {
                Log.d("VRAJTEST", "Name of the person is $it")
            }
        }
    }
}