package by.loqueszs.spacexfan.presentation.rockets

import android.view.ViewGroup
import by.loqueszs.spacexfan.R
import by.loqueszs.spacexfan.core.database.entities.RocketEntity
import by.loqueszs.spacexfan.core.ui.ViewBindingViewHolder
import by.loqueszs.spacexfan.databinding.RocketCardBinding
import com.bumptech.glide.Glide
import com.google.android.material.checkbox.MaterialCheckBox

class RocketViewHolder(
    parent: ViewGroup,
    private val onClick: (id: String) -> Unit,
    private val addToFavorites: (rocket: RocketEntity) -> Unit,
    private val removeFromFavorites: (id: String) -> Unit
) : ViewBindingViewHolder<RocketCardBinding>(parent, RocketCardBinding::inflate) {

    fun bind(id: String, name: String, url: String, favorite: Boolean) {
        binding.root.setOnClickListener {
            onClick(id)
        }

        binding.favorite.isChecked = favorite

        binding.name.text = name

        binding.favorite.addOnCheckedStateChangedListener { checkBox, state ->
            when (state) {
                MaterialCheckBox.STATE_CHECKED -> {
                    addToFavorites(
                        RocketEntity(
                            id = id,
                            name = name,
                            image = url
                        )
                    )
                }
                MaterialCheckBox.STATE_UNCHECKED -> {
                    removeFromFavorites(id)
                }
            }
        }

        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.rocket_placeholder)
            .into(binding.rocketImage)
    }
}
