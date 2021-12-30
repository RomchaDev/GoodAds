package org.romeo.goodads.main

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.romeo.goodads.R
import org.romeo.goodads.databinding.ActivityMainBinding
import org.romeo.goodads.navigation.NavigationContainerActivity

class MainActivity : NavigationContainerActivity(
    R.id.fragment_container
) {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initBottomNavigation()
    }


    private fun initBottomNavigation() {
        val navController =
            Navigation.findNavController(this, R.id.fragment_container)

        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottom_navigation)

        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.adFullFragment ||
                destination.id == R.id.createEditAdFragment ||
                destination.id == R.id.createEditAdFragment ||
                destination.id == R.id.adListItemFullFragment ||
                destination.id == R.id.adRequestFragment ||
                destination.id == R.id.userRequestFragment ||
                destination.id == R.id.createDistributionDialogFragment
            ) {
                binding?.bottomNavigation?.visibility = View.GONE
                bottomNavigationView.visibility = View.GONE
            } else {
                binding?.bottomNavigation?.visibility = View.VISIBLE
                bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}