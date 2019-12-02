package com.example.rxjavatest

import io.reactivex.Observable
import org.junit.Test

import org.junit.Assert.*
import retrofit2.http.GET

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
//        Observable.just("Apple", "Banana", "Mangoes").subscribe(
//            {value -> println("value: $value")},
//            {error -> println("error: $error")},
//            { println("Completed")}
//        )



        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_kotlin(){
        val withType: (Int) -> String = {number -> val newNum = number * 2 ; "With type: $newNum"}
        val withoutType = {number: Int -> val newNum = number * 2; "Without type: $newNum"}
        val another : String.(Int) -> String = { this + it }

        println(withType(90))
        println(withoutType(90))
        println("another: ${another("as",6)}")
        println("another two: ${"hello".another(8)}")
    }
}
