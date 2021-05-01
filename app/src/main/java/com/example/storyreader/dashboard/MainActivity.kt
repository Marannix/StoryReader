package com.example.storyreader.dashboard

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.storyreader.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stuff()
    }

    fun stuff() {
        viewModel.getRailwayChildrenBook().subscribe {
            when (it) {
                DashboardState.Loading -> {
                    Log.d("loading", "hi")
                }
                is DashboardState.RailwayChildrenBook -> {
                    it.book.forEach {
                        println(it.key + " " + it.value)
                    }
                }
                is DashboardState.Error -> {
                    Log.d("error", it.message ?: "no error")
                }
            }
        }
    }
}