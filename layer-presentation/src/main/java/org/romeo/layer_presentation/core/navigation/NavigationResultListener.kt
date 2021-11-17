package org.romeo.layer_presentation.core.navigation

interface NavigationResultListener<T> {
    fun onNavigationResult(result: T?)
}