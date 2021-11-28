package org.romeo.layer_presentation.main.create_edit_ad

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.main.BaseFragment
import org.romeo.layer_presentation.core.navigation.CREATE_EDIT_AD_IN
import org.romeo.layer_presentation.core.utils.getByteArray
import org.romeo.layer_presentation.databinding.FragmentCreateEditAdBinding

class CreateEditAdFragment :
    BaseFragment<FragmentCreateEditAdBinding, ImagesUrlsViewState, CreateEditAdViewModel>(
        R.layout.fragment_create_edit_ad
    ) {

    override val viewModel: CreateEditAdViewModel by viewModel {
        val ad = arguments?.getParcelable<Ad>(CREATE_EDIT_AD_IN)
        parametersOf(ad)
    }

    private val getImagesLauncher =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
            try {
                if (uris.size != 0) {
                    (binding.vpImages.adapter as CreateEditAdPagerAdapter).uris = uris
                    viewModel.images = uris.map { it.getByteArray(requireContext())!! }
                }
            } catch (e: NullPointerException) {
                showMessage("Sorry, something went wrong")
                e.printStackTrace()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.indicator.setViewPager(binding.vpImages)
        binding.vpImages.adapter = CreateEditAdPagerAdapter()
        binding.viewModel = viewModel
        binding.tvSelectImage.setOnClickListener {
            getImagesLauncher.launch("image/*")
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun renderSuccess(data: ImagesUrlsViewState) {
        super.renderSuccess(data)
        (binding.vpImages.adapter as CreateEditAdPagerAdapter)
            .uris = data.imageUrls.map { Uri.parse(it) }
    }
}