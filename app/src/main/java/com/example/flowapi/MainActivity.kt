package com.example.flowapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
    //When to use Filter operator ?
/*
*  - To make any changes/update/mapping which Flow is emitting, we can use Map for that
*  - Actual output jode chhedchhad kari, desired output display karva
* */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = flowOf(1,2,3,4,5,6,7,8,9,10,11,12,13,43,65,75,99,122)
            .flowOn(Dispatchers.IO) //process background ma karava

//        lifecycleScope.launchWhenStarted {
//            data
//                .filter {
//                    it%2 != 0   //odd
//                }
//                .collect{
//                    Log.d("VRAJTEST", "it%2 != 0 => $it")
//                }
//        }
        /*runBlocking {
            data
                .filter { value->
                    value%2 == 0    // Based on the condition you put here..data will get collected or ignored if it doesn't satisfy the condition
                }
                .collect {
                Log.d("VRAJTEST", "Name of the person is $it")
            }
        }*/

        /*testIntermediateFlowOperators()*/

        testErrorHandling()
    }

/*        private fun testIntermediateFlowOperators(){
            lifecycleScope.launchWhenStarted {
                (1..5).asFlow()
//                    .drop(2)    //1. removes first 2 items
                    .take(3)    //2. take first 3 items
//                    .map {    //3. to transform flow
//                        "your number: $it * 2 => ${it*2}"
//                    }
//                    .flatMapConcat {    //4. To flat flow of a flow, you can modify data and EMIT it as well
//                        flow {
//                            emit(it)
//                            emit(it*it)
//                        }
//                    }
                    .collect{
                        Log.d("VRAJTEST", "With drop:: $it")
                    }
            }
        }*/

        private fun testErrorHandling() {
            lifecycleScope.launchWhenStarted {
                (1..5)
                    .asFlow()
                    .onEach {
                        if (it == 3) throw RuntimeException("Error on $it")
                    }
                    .catch {    //3 aavta error throw thashe, and niche no block execute thashe
                        emitAll((6..10).asFlow())
                    }
                    .collect{
                        Log.d("VRAJTEST", "testErrorHandling: $it")
                    }
            }
        }
}