package by.loqueszs.spacexfan.presentation.launchesdetail

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
class LaunchDetailViewModel @Inject constructor(
    private val repository: SpaceXFanRepository
) : ViewModel() {

    private val _launch by lazy {
        MutableLiveData<Launch>()
    }
    val launch: LiveData<Launch>
        get() = _launch

    private val compositeDisposable = CompositeDisposable()

    fun getLaunch(id: String) {
        repository.getLaunchByID(id)
            .subscribe(
                {
                    _launch.value = it
                },
                {

                }
            ).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
