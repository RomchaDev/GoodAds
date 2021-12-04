package org.romeo.layer_presentation.main.ad_list_item_full

import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.main.BaseAdFullFragment
import org.romeo.layer_presentation.core.navigation.AD_FULL_KEY

class AdListItemFullFragment : BaseAdFullFragment() {

    override val bottomButtonText: String by lazy { getString(R.string.accept) }

    override val viewModel: AdListItemFullViewModel by viewModel {
        parametersOf(arguments?.getString(AD_FULL_KEY))
    }

}