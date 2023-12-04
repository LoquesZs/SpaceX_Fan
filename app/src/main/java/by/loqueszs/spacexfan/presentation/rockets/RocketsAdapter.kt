package by.loqueszs.spacexfan.presentation.rockets

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.loqueszs.spacexfan.core.database.entities.RocketEntity
import by.loqueszs.spacexfan.core.network.models.rockets.Rocket

class RocketsAdapter(
    private val onClick: (id: String) -> Unit,
    private val addToFavorites: (rocket: RocketEntity) -> Unit,
    private val removeFromFavorites: (id: String) -> Unit
) : ListAdapter<Pair<Rocket, Boolean>, RocketViewHolder>(RocketsDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        return RocketViewHolder(parent, onClick, addToFavorites, removeFromFavorites)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val rocket = getItem(position)
        holder.bind(
            id = rocket.first.id,
            name = rocket.first.name.orEmpty(),
            url = rocket.first.flickrImages.firstOrNull(),
            favorite = rocket.second
        )
    }

    private class RocketsDiffUtilCallback : DiffUtil.ItemCallback<Pair<Rocket, Boolean>>() {
        override fun areItemsTheSame(oldItem: Pair<Rocket, Boolean>, newItem: Pair<Rocket, Boolean>): Boolean {
            return oldItem.first.id == newItem.first.id
        }

        override fun areContentsTheSame(oldItem: Pair<Rocket, Boolean>, newItem: Pair<Rocket, Boolean>): Boolean {
            return oldItem.first == newItem.first
        }
    }
}