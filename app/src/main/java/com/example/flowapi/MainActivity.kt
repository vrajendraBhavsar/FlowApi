package com.example.flowapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*2. Collect data*/
        runBlocking {   //data collection Main thread ma thashe
            getData()
                .catch { e ->   //to handle exception
                    Log.d("VRAJTEST", "onCreate: ${e.message}")
                }
                .collect { number ->   //Collect is a Suspeding function
//                Toast.makeText(baseContext, "Emitted number:: $number", Toast.LENGTH_SHORT).show()
                    Log.d("VRAJTEST", "onCreate: Emitted number:: $number")
                }
        }
    }

    /*1. Flow Builder*/
    private fun getData(): Flow<Int> = flow {
        //we'll put data here jene Emit karavo hoy
        for (i in 1..5) {
            delay(1000)  //0.5 sec no delay, pachhi j niche ni value emit thashe
            emit(i) //data will get emitted, only when called by Collector's "collect" method
        }
    }
//        .flowOn(Dispatchers.IO)    //flowOn operator can be used to change Context of upsteam(flowOn uper no code)..now data background ma emit thashe //????????????
}