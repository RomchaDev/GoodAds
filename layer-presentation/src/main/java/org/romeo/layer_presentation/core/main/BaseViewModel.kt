package org.romeo.layer_presentation.core.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_domain.app_state.AppStateEntity

abstract class BaseViewModel<D : AppStateEntity>(
    protected val navigator : AppNavigator
) : ViewModel() {

    protected val mStateLiveData = MutableLiveData<AppState<D>>()
    val stateLiveData get() = mStateLiveData as LiveData<AppState<D>>

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected open fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    open fun handleError(error: Throwable) {
/*        if (error is NoAuthException) {
            navigator.toLoginScreen()
            //Token will be removed in LoginViewModel
        }*/

        mStateLiveData.postValue(AppState.Error(error))
    }

    open fun onViewInit() {
    }

    protected fun runAsync(block: suspend () -> Unit) =
        viewModelCoroutineScope.launch {
            block()
        }


    protected fun <T> runAsyncWithResult(block: suspend () -> T) =
        viewModelCoroutineScope.async {
            block()
        }
}
