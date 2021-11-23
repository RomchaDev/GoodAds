package org.romeo.goodads.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.NavigationCommand
import org.romeo.layer_presentation.core.navigation.NavigationResultListener

//action_createEditAdFragment_to_fragment_home
//action_fragment_home_to_createEditAdFragment

class AndroidNavigator : AppNavigator {

    var navHostActivity: FragmentActivity? = null
    var hostFragmentId: Int? = null

    private val navController: NavController
        get() = Navigation.findNavController(navHostActivity!!, hostFragmentId!!)

    override fun navigate(command: NavigationCommand, args: Bundle?) {
        try {
            command.args = args
            command.execute(navController)
        } catch (e: NullPointerException) {
            Log.d(TAG, "navHostActivity and/or  hostFragmentId is not assigned")
        }
    }

    override fun <T> setResult(key: String, value: T) {
        navController.previousBackStackEntry?.savedStateHandle?.set(key, value)
    }

    override fun <T> getResult(key: String) =
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)?.value

    override fun <T> subscribeToResult(listener: NavigationResultListener<T>, key: String) {
        val liveData = navController.currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)


        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                listener.onNavigationResult(t)
                liveData?.removeObserver(this)
            }
        }

        liveData?.observeForever(observer)
    }

    override fun back() {
        val previous = navController.previousBackStackEntry?.destination?.id
        previous?.let {
            navController.navigate(previous)
        }
    }

    override fun onDestroyNavigation() {
        navHostActivity = null
    }

    companion object {
        private const val TAG = "AndroidNavigator"
    }
}