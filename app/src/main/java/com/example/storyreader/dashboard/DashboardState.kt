package com.example.storyreader.dashboard

sealed class DashboardState {
    object Loading : DashboardState()
    data class RailwayChildrenBook(val book: HashMap<String, Int>) : DashboardState()
    data class Error(val message: String?) : DashboardState()
}