package org.romeo.layer_presentation.main.create_distribution

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.utils.toPx
import org.romeo.layer_presentation.databinding.FragmentCreateDistributionBinding
import java.lang.NumberFormatException

class CreateDistributionDialogFragment : DialogFragment() {
    private val viewModel: CreateDistributionViewModel by viewModel()
    private var bindingNullable: FragmentCreateDistributionBinding? = null
    private val binding get() = bindingNullable!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil.inflate<FragmentCreateDistributionBinding>(
        inflater,
        R.layout.fragment_create_distribution,
        container,
        false
    ).apply { bindingNullable = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(DIALOG_WIDTH.toPx.toInt(), DIALOG_HEIGHT.toPx.toInt())
        }

        binding.btnCreate.setOnClickListener {
            try {
                val price = binding.etPriceForOneAd.editText?.text?.toString()?.toInt()
                val number = binding.etAdvertisersNumber.editText?.text?.toString()?.toInt()

                if (price != null && number != null)
                    viewModel.onCreatePressed(price, number)
            } catch (e: NumberFormatException) {
                showMessage("Data cannot be empty")
                e.printStackTrace()
            }
        }

        binding.btnCancel.setOnClickListener {
            viewModel.onCancelPressed()
        }

        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingNullable = null
    }

    private fun showMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        private const val DIALOG_WIDTH = 340
        private const val DIALOG_HEIGHT = 330
    }
}