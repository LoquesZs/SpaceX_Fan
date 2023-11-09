package by.loqueszs.spacexfan.presentation.rocketsdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.loqueszs.spacexfan.core.database.entities.RocketEntity
import by.loqueszs.spacexfan.databinding.FragmentRocketDetailBinding
import com.bumptech.glide.Glide
import com.google.android.material.checkbox.MaterialCheckBox
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

@AndroidEntryPoint
class RocketDetailFragment : Fragment() {

    private val args by navArgs<RocketDetailFragmentArgs>()
    private val viewModel by viewModels<RocketDetailViewModel>()
    private var _binding: FragmentRocketDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRocketDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ImageListAdapter()

        binding.images.adapter = adapter
        binding.images.layoutManager = LinearLayoutManager(this.context)

        viewModel.getRocket(args.id)
            .doOnSuccess {
                binding.rocket.favorite.isChecked = viewModel.isFavorite(it.id)
            }
            .subscribe(
                { rocket ->
                    Glide.with(this)
                        .load(rocket.flickrImages.first())
                        .into(binding.rocket.rocketImage)
                    binding.rocket.favorite.addOnCheckedStateChangedListener { checkBox, state ->
                        when (state) {
                            MaterialCheckBox.STATE_CHECKED -> {
                                viewModel.addToFavorites(RocketEntity(rocket.id, rocket.name.orEmpty(), rocket.flickrImages.first()))
                            }
                            MaterialCheckBox.STATE_UNCHECKED -> {
                                viewModel.deleteFromFavorites(rocket.id)
                            }
                        }
                    }
                    binding.height.text = "${rocket.height?.meters} m/${rocket.height?.feet} ft"
                    binding.diameter.text = "${rocket.diameter?.meters} m/${rocket.diameter?.feet} ft"
                    binding.mass.text = "${rocket.mass?.kg} kg/${rocket.mass?.lb} lb"
                    Log.d(javaClass.simpleName, rocket.flickrImages.toString())
                    adapter.submitList(rocket.flickrImages)
                    adapter.notifyDataSetChanged()
                },
                {
                    Log.d(javaClass.simpleName, it.stackTraceToString())
                }
            ).addTo(compositeDisposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}
