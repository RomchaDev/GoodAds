package org.romeo.goodads.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.findNavController
import org.koin.android.ext.android.inject
import org.romeo.goodads.R
import org.romeo.goodads.databinding.ActivityMainBinding
import org.romeo.goodads.navigation.AndroidNavigator
import org.romeo.layer_presentation.core.navigation.commands.impl.AnyToHomeCommand

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val navigator : AndroidNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navigator.navController = findNavController(R.id.fragment_container)

        initBottomNavigation()
    }


    private fun initBottomNavigation() {
        val navController =
            Navigation.findNavController(this, R.id.fragment_container)

        binding?.bottomNavigation?.let {
            NavigationUI.setupWithNavController(it, navController)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.adFullFragment ||
                destination.id == R.id.createEditAdFragment ||
                destination.id == R.id.createEditAdFragment ||
                destination.id == R.id.adListItemFullFragment ||
                destination.id == R.id.adRequestFragment ||
                destination.id == R.id.userRequestFragment ||
                destination.id == R.id.loginFragment ||
                destination.id == R.id.createDistributionDialogFragment
            ) {
                binding?.bottomNavigation?.visibility = View.GONE
            } else {
                binding?.bottomNavigation?.visibility = View.VISIBLE
            }

            if (destination.id == R.id.fragment_home || destination.id == R.id.guestLoginFragment) {
                afterHomeCounter = 0
                isActionBack = false
            }
            else if (!isActionBack)
                afterHomeCounter++
        }
    }

    override fun onBackPressed() {
        isActionBack = true

        if (afterHomeCounter == 1) binding?.bottomNavigation?.selectedItemId = R.id.fragment_home
        else {
            afterHomeCounter--
            super.onBackPressed()
        }
    }

    companion object {
        var afterHomeCounter = 0
        var isActionBack = false
    }
}