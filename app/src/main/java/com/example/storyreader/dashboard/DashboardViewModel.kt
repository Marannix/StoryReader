package com.example.storyreader.dashboard

import com.example.data.book.usecase.GetRailwayChildrenWordsUseCase
import com.example.storyreader.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val railwayChildrenWordsUseCase: GetRailwayChildrenWordsUseCase) : BaseViewModel() {

    fun getRailwayChildrenBook() : Observable<DashboardState> {
        return railwayChildrenWordsUseCase.invoke()
            .observeOn(AndroidSchedulers.mainThread())
            .map { state ->
                when (state) {
                    is GetRailwayChildrenWordsUseCase.RailwayChildrenDataState.Success -> {
                        DashboardState.RailwayChildrenBook(state.listOfWords)
                    }
                    is GetRailwayChildrenWordsUseCase.RailwayChildrenDataState.Error -> {
                        DashboardState.Error(state.message)
                    }
                }
            }
    }

//    sealed class ViewEvent {
//        data class Error(val message: String?) : ViewState()
//    }
}