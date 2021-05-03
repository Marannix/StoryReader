package com.example.storyreader.dashboard

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.storyreader.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

private const val GRID_SIZE = 2

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: DashboardViewModel by lazy {
        ViewModelProvider(this).get<DashboardViewModel>(
            DashboardViewModel::class.java
        )
    }
    private val dashboardAdapter by lazy { DashboardAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAdapter()
        getRailwayChildrenBook()
        observeRailwayChildrenBook()
    }

    private fun getRailwayChildrenBook() {
        viewModel.getRailwayChildrenBook()
    }

    private fun observeRailwayChildrenBook() {
        viewModel.viewState.observe(this, Observer { state ->
            when (state) {
                DashboardState.Loading -> {
                    progress.visibility = View.VISIBLE
                }
                is DashboardState.RailwayChildrenBook -> {
                    progress.visibility = View.INVISIBLE
                    dashboardAdapter.setData(state.book)
                }
                is DashboardState.Error -> {
                    progress.visibility = View.INVISIBLE
                    state.bookFromLocalStorage.let { formattedBook ->
                        if (formattedBook != null) {
                            Toast.makeText(this, "Loading from local storage", Toast.LENGTH_SHORT).show()
                            dashboardAdapter.setData(formattedBook)
                        }
                    }
                }
            }
        })
    }

    private fun setAdapter() {
        recyclerView.layoutManager = GridLayoutManager(this, GRID_SIZE)
        recyclerView.adapter = dashboardAdapter
    }
}