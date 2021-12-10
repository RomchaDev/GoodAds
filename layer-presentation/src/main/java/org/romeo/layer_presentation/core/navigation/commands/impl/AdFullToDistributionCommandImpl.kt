package org.romeo.layer_presentation.core.navigation.commands.impl

import android.os.Bundle
import androidx.navigation.NavController
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AdFullToDistributionCommand

class AdFullToDistributionCommandImpl : AdFullToDistributionCommand {
    override var args: Bundle? = null

    override fun execute(navController: NavController) {
        navController.navigate(R.id.action_adFullFragment_to_createDistributionDialogFragment)
    }
}