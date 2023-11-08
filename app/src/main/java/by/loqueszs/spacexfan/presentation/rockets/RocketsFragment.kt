package by.loqueszs.spacexfan.presentation.rockets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.loqueszs.spacexfan.databinding.FragmentRocketsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketsFragment : Fragment() {

    private var _binding: FragmentRocketsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<RocketsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRocketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RocketsAdapter(
            onClick = {
                findNavController().navigate(
                    RocketsFragmentDirections.actionRocketsFragmentToRocketDetailFragment(it)
                )
            },
            addToFavorites = { rocket ->
                viewModel.addToFavorites(rocket)
            },
            removeFromFavorites = { id ->
                viewModel.removeFromFavorites(id)
            }
        )
        binding.rocketsRecyclerView.adapter = adapter
        binding.rocketsRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.result.observe(viewLifecycleOwner) { rockets ->
            binding.progressBar.visibility = View.INVISIBLE
            binding.rocketsRecyclerView.visibility = View.VISIBLE
            adapter.submitList(rockets)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
