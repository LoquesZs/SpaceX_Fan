package by.loqueszs.spacexfan.presentation.launches

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.loqueszs.spacexfan.core.network.models.launches.Launch

class LaunchesAdapter(
    private val onClick: (id: String) -> Unit
) : ListAdapter<Launch, LaunchViewHolder>(LaunchDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LaunchViewHolder(parent, onClick)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        val launch = getItem(position)
        holder.bind(
            id = launch.id,
            name = launch.name.orEmpty(),
            description = launch.details.orEmpty(),
            url = launch.links?.patch?.small.orEmpty()
        )
    }

    private class LaunchDiffUtilCallback : DiffUtil.ItemCallback<Launch>() {
        override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
            return oldItem == newItem
        }
    }
}