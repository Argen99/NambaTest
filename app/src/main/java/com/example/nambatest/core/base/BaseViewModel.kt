package com.example.nambatest.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.core.Either
import com.example.nambatest.core.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> mutableUiStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())

    protected fun <T> Flow<Either<T>>.gatherRequest(
        state: MutableStateFlow<UIState<T>>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UIState.Loading()
            this@gatherRequest.collect {
                when (it) {
                    is Either.Error -> {
                        state.value =
                            UIState.Error(it.message)
                    }
                    is Either.Success -> {
                        state.value = UIState.Success(it.data)
                    }
                }
            }
        }
    }
}