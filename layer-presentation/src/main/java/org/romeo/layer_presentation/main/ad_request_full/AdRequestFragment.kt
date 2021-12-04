package org.romeo.layer_presentation.main.ad_request_full

import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.main.BaseAdFullFragment
import org.romeo.layer_presentation.core.navigation.AD_FULL_KEY

class AdRequestFragment : BaseAdFullFragment() {

    override val bottomButtonText: String by lazy { getString(R.string.accept) }

    override val viewModel: AdRequestViewModel by viewModel {
        parametersOf(arguments?.getString(AD_FULL_KEY))
    }

}