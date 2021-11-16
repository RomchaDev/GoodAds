package org.romeo.goodads.navigation

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject

class NavigationContainerActivity(
    private val hostFragmentId: Int
) : AppCompatActivity() {
    private val navigator : AndroidNavigator by inject()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.navHostActivity = this
        navigator.hostFragmentId = hostFragmentId
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        navigator.onDestroyNavigation()
    }
}