package by.loqueszs.spacexfan.presentation.launches

import android.view.ViewGroup
import by.loqueszs.spacexfan.R
import by.loqueszs.spacexfan.core.ui.ViewBindingViewHolder
import by.loqueszs.spacexfan.databinding.LaunchCardBinding
import com.bumptech.glide.Glide

class LaunchViewHolder(
    parent: ViewGroup,
    private val onClick: (id: String) -> Unit
) : ViewBindingViewHolder<LaunchCardBinding>(parent, LaunchCardBinding::inflate) {

    fun bind(id: String, name: String, url: String) {
        binding.explore.setOnClickListener {
            onClick(id)
        }

        binding.name.text = name

        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.launch_placeholder)
            .into(binding.imageView)
    }

}