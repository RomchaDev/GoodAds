package org.romeo.layer_presentation.core.navigation

import androidx.navigation.NavController

interface NavigationCommand {
    fun execute(navController: NavController)
}