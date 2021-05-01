package com.example.storyreader.dashboard

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyreader.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : DashboardViewModel by lazy {
        ViewModelProvider(this).get<DashboardViewModel>(
            DashboardViewModel::class.java
        )
    }

    private val dashboardAdapter = DashboardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAdapter()
        stuff()
    }

    fun stuff() {
        viewModel.getRailwayChildrenBook()
        viewModel.viewState.observe(this, Observer { state ->
            when (state) {
                DashboardState.Loading -> {
                    Log.d("loading", "hi")
                }
                is DashboardState.RailwayChildrenBook -> {
                    Log.d("loaded", "hi")
                    dashboardAdapter.setData(state.book)
                }
                is DashboardState.Error -> {
                    Log.d("error", state.message ?: "no error")
                }
            }

        })
    }

    private fun setAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.adapter = dashboardAdapter
    }
}