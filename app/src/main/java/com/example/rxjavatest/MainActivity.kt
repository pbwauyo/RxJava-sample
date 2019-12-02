package com.example.rxjavatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavatest.adapters.MovieAdapter
import com.example.rxjavatest.apiClient.ApiClient
import com.example.rxjavatest.apiInterface.ApiService
import com.example.rxjavatest.constants.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    var subscriptions = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val apiService: ApiService = ApiClient.getClient().create(ApiService::class.java)

        val subscribe = apiService.getTopRatedMovies(Constants.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { val adapter = MovieAdapter(it, this); recyclerView.adapter = adapter},
                { error -> println("Error: $error")},
                { println("Completed")}
            )

        subscriptions.add(subscribe)
    }

    override fun onDestroy() {
        super.onDestroy()

        subscriptions.clear()
    }
}
