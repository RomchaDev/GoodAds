package org.romeo.layer_presentation.main.ad_screen

import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.main.BaseAdFullFragment
import org.romeo.layer_presentation.core.navigation.AD_FULL_KEY

class MyAdFullFragment : BaseAdFullFragment() {

    override val bottomButtonText: String by lazy { getString(R.string.distribution) }

    override val viewModel: MyAdFullViewModel by viewModel {
        parametersOf(arguments?.getString(AD_FULL_KEY))
    }

}