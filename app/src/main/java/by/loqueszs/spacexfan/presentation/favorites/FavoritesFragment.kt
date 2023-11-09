package by.loqueszs.spacexfan.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.loqueszs.spacexfan.R
import by.loqueszs.spacexfan.core.network.models.rockets.Rocket
import by.loqueszs.spacexfan.databinding.FragmentFavoritesBinding
import by.loqueszs.spacexfan.presentation.rockets.RocketsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<FavoritesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RocketsAdapter(
            onClick = {
                findNavController().navigate(
                    FavoritesFragmentDirections.actionFavoritesFragmentToRocketDetailFragment(it)
                )
            },
            addToFavorites = { },
            removeFromFavorites = {
                viewModel.deleteFromFavorites(it)
            }
        )
        binding.favoritesRecyclerView.adapter = adapter
        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        viewModel.favorites.observe(viewLifecycleOwner) { rockets ->
            binding.progressBar.visibility = View.INVISIBLE
            if (rockets.isEmpty()) {
                binding.errorStub.emptyStubTextview.text = getString(
                    R.string.you_have_no_rockets_added_to_favorites_list
                )
                adapter.submitList(emptyList())
                binding.favoritesRecyclerView.visibility = View.GONE
                binding.errorStub.root.visibility = View.VISIBLE
            } else {
                binding.favoritesRecyclerView.visibility = View.VISIBLE
                adapter.submitList(
                    rockets.map {
                        Pair(
                            Rocket(id = it.id, name = it.name, flickrImages = listOf(it.image)),
                            true
                        )
                    }
                )
                binding.errorStub.root.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
