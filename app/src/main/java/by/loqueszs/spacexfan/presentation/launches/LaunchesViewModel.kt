package by.loqueszs.spacexfan.presentation.launches

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.loqueszs.spacexfan.core.network.models.launches.Launch
import by.loqueszs.spacexfan.domain.SpaceXFanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val repository: SpaceXFanRepository
) : ViewModel() {

    private val _launches by lazy {
        MutableLiveData<List<Launch>>()
    }
    val launches: LiveData<List<Launch>>
        get() = _launches

    private val compositeDisposable = CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    init {
        repository.getLaunches()
            .subscribe(
                { launchesList ->
                    _launches.value = launchesList
                },
                {
                    Log.d(javaClass.name, it.message.orEmpty())
                }
            ).addTo(compositeDisposable)
    }
}