package org.romeo.layer_presentation.core.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_domain.app_state.AppStateEntity

abstract class BaseViewModel<D : AppStateEntity>(

) : ViewModel() {

    protected abstract val navigator : AppNavigator

    protected val mStateLiveData = MutableLiveData<AppState<D>>()
    val stateLiveData get() = mStateLiveData as LiveData<AppState<D>>

    private val ioCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private val mainCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected open fun cancelJob() {
        ioCoroutineScope.coroutineContext.cancelChildren()
    }

    open fun handleError(error: Throwable) {
/*        if (error is NoAuthException) {
            navigator.toLoginScreen()
            //Token will be removed in LoginViewModel
        }*/

        error.printStackTrace()
        mStateLiveData.postValue(AppState.Error(error))
    }

    open fun onViewInit() {
    }

    protected fun runAsync(block: suspend () -> Unit) =
        ioCoroutineScope.launch {
            block()
        }


    protected fun <T> runAsyncWithResult(block: suspend () -> T) =
        ioCoroutineScope.async {
            block()
        }

    protected fun runOnMainThread(block: suspend () -> Unit) =
        mainCoroutineScope.launch {
            block()
        }
}
