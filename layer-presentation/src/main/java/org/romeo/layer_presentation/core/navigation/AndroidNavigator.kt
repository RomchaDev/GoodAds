package org.romeo.layer_presentation.core.navigation

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation

//action_createEditAdFragment_to_fragment_home
//action_fragment_home_to_createEditAdFragment

class AndroidNavigator : AppNavigator {

    var navHostActivity: FragmentActivity? = null
    var hostFragmentId: Int? = null

    override fun navigate(command: NavigationCommand) {
        try {
            command.execute(Navigation.findNavController(navHostActivity!!, hostFragmentId!!))
        } catch(e : NullPointerException) {
            Log.d(TAG, "navHostActivity and/or  hostFragmentId is not assigned")
        }
    }

    override fun onDestroyNavigation() {
        navHostActivity = null
    }

    companion object {
        private const val TAG = "AndroidNavigator"
    }
}