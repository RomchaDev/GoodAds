package org.romeo.layer_presentation.core.navigation

import android.os.Bundle

//action_createEditAdFragment_to_fragment_home
//action_fragment_home_to_createEditAdFragment

interface AppNavigator {
    fun navigate(command: NavigationCommand, arguments: Bundle? = null)
    fun <T>setResult(key: String, value: T)
    fun <T>getResult(key: String) : T?
    fun <T>subscribeToResult(listener: NavigationResultListener<T>, key: String)
    fun back()
    fun onDestroyNavigation()
}