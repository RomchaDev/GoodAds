package org.romeo.layer_presentation.main.create_edit_ad

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.romeo.layer_presentation.databinding.ItemViewPagerAdBinding

class CreateEditAdPagerAdapter(
    uris: List<Uri> = listOf()
) : RecyclerView.Adapter<CreateEditAdPagerAdapter.AdViewPagerViewHolder>() {

    var uris = uris
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewPagerViewHolder {
        val binding = ItemViewPagerAdBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return AdViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdViewPagerViewHolder, position: Int) {
            holder.binding.ivAd.setImageURI(uris[position])
    }

    override fun getItemCount(): Int = this.uris.size

    inner class AdViewPagerViewHolder(val binding: ItemViewPagerAdBinding) :
        RecyclerView.ViewHolder(binding.root)
}