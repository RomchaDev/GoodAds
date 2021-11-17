package org.romeo.goodads.navigation

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject

class NavigationContainerActivity(
    private val hostFragmentId: Int,
    private val bottomNavigationId: Int
) : AppCompatActivity() {
    private val navigator : AndroidNavigator by inject()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()

        navigator.navHostActivity = this
        navigator.hostFragmentId = hostFragmentId
    }

    private fun initBottomNavigation() {
        val navController =
            Navigation.findNavController(this, hostFragmentId)

        val bottomNavigationView =
            findViewById<BottomNavigationView>(bottomNavigationId)

        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        navigator.onDestroyNavigation()
    }
}