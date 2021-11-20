package org.romeo.layer_presentation.main.ad_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.romeo.layer_presentation.databinding.ItemViewPagerAdBinding

class AdViewPagerAdapter(
    private val images: List<String>
) : RecyclerView.Adapter<AdViewPagerAdapter.AdViewPagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewPagerViewHolder {
        val binding = ItemViewPagerAdBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return AdViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdViewPagerViewHolder, position: Int) {
        holder.binding.imageUrl = images[position]
    }

    override fun getItemCount(): Int = images.size

    inner class AdViewPagerViewHolder(val binding: ItemViewPagerAdBinding) :
        RecyclerView.ViewHolder(binding.root)
}