package com.meta.metatools.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel<INTENT : ViewIntent, STATE : ViewState> : ViewModel() {

    //var mutableState = Channel<STATE>(Channel.BUFFERED)
    protected var mutableState = MutableLiveData<STATE>()

    fun initMutable(){
        mutableState = MutableLiveData<STATE>()
    }

    val state: LiveData<STATE>
        get() {
            return mutableState
        }


    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.Default) {
            block()
        }
    }

    fun dispatchIntent(intent: INTENT) {
        handleIntent(intent)
    }

    abstract fun handleIntent(intent: INTENT)
    abstract fun initialState(): STATE
}