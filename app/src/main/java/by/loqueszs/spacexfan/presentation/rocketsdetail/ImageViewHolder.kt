package by.loqueszs.spacexfan.presentation.rocketsdetail

import android.view.ViewGroup
import by.loqueszs.spacexfan.R
import by.loqueszs.spacexfan.core.ui.ViewBindingViewHolder
import by.loqueszs.spacexfan.databinding.ImageCardBinding
import com.bumptech.glide.Glide

class ImageViewHolder(parent: ViewGroup) : ViewBindingViewHolder<ImageCardBinding>(parent, ImageCardBinding::inflate) {

    fun bind(url: String) {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.rocket_placeholder)
            .into(binding.image)
    }
}