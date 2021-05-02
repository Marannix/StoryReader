package com.example.storyreader.dashboard

import com.example.domain.common.BookDetails

sealed class DashboardState {
    object Loading : DashboardState()
    data class RailwayChildrenBook(val book: HashMap<String, BookDetails>) : DashboardState()
    data class Error(val message: String?, val bookFromLocalStorage: HashMap<String, BookDetails>?) : DashboardState()
}