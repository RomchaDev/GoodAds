package org.romeo.layer_presentation.core.app_state

import org.romeo.layer_domain.app_state.AppStateEntity


sealed class AppState<out T : AppStateEntity> {
    data class Success<out T : AppStateEntity>(val data: T) : AppState<T>()
    data class Error<out T : AppStateEntity>(val error: Throwable) : AppState<T>()
    data class Message<out T : AppStateEntity>(val message: String) : AppState<T>()
    class Loading<out T : AppStateEntity> : AppState<T>()
}