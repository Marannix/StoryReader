package com.example.storyreader.dashboard

import androidx.lifecycle.MutableLiveData
import com.example.data.book.usecase.GetRailwayChildrenWordsUseCase
import com.example.storyreader.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val railwayChildrenWordsUseCase: GetRailwayChildrenWordsUseCase) : BaseViewModel() {
    val viewState = MutableLiveData<DashboardState>()

    fun getRailwayChildrenBook() {
        if (viewState.value == null) {
            railwayChildrenWordsUseCase.invoke()
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
                .doOnSubscribe { viewState.value = DashboardState.Loading }
                .subscribe {
                    viewState.value = it
                }.addDisposable()
        }
    }
}