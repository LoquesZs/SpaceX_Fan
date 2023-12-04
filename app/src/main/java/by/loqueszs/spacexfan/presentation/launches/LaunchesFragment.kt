package by.loqueszs.spacexfan.presentation.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.loqueszs.spacexfan.R
import by.loqueszs.spacexfan.databinding.FragmentLaunchesBinding
import by.loqueszs.spacexfan.databinding.FragmentRocketsBinding
import by.loqueszs.spacexfan.presentation.rockets.RocketsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchesFragment : Fragment() {

    private var _binding: FragmentLaunchesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<LaunchesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLaunchesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.launchesRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = LaunchesAdapter { id: String ->
            findNavController().navigate(LaunchesFragmentDirections.actionLaunchesFragmentToLaunchDetailFragment(id))
        }
        binding.launchesRecyclerView.adapter = adapter

        viewModel.launches.observe(viewLifecycleOwner) { launches ->
            binding.progressBar.visibility = View.INVISIBLE
            binding.launchesRecyclerView.visibility = View.VISIBLE
            adapter.submitList(launches)
        }
    }

    override fun onStart() {
        super.onStart()
    }
}