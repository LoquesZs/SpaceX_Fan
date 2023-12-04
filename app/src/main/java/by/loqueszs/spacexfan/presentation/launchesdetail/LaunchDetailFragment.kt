package by.loqueszs.spacexfan.presentation.launchesdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.loqueszs.spacexfan.databinding.FragmentLaunchDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchDetailFragment : Fragment() {

    private var _binding: FragmentLaunchDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LaunchDetailViewModel by viewModels()

    private val args = navArgs<LaunchDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getLaunch(args.value.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaunchDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.launch.observe(viewLifecycleOwner) { launch ->
            Log.d(javaClass.simpleName, launch.toString())
            Glide.with(requireContext())
                .load(
                    if (launch.links?.flickr?.original.isNullOrEmpty()) {
                        launch.links?.patch?.large.orEmpty()
                    } else {
                        launch.links?.flickr?.original?.first().orEmpty()
                    }
                )
                .into(binding.imageView)
            binding.name.text = launch.name
            binding.description.text = launch.details
            binding.date.text = launch.dateLocal
        }
    }
}
