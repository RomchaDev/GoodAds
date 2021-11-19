package org.romeo.layer_presentation.core.navigation.commands.impl

import androidx.navigation.NavController
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AnyToChoseAdCommand

class AnyToChoseAdCommandImpl : AnyToChoseAdCommand {
    override fun execute(navController: NavController) {
        navController.navigate(R.id.action_global_chooseAdFragment)
    }
}