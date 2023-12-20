package by.loqueszs.spacexfan.presentation.launches

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.loqueszs.spacexfan.core.network.models.launches.Launch
import by.loqueszs.spacexfan.domain.SpaceXFanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val repository: SpaceXFanRepository
) : ViewModel() {

    private val _launches by lazy {
        MutableLiveData<List<Launch>>()
    }
    val launches: LiveData<List<Launch>>
        get() = _launches

    init {
        viewModelScope.launch {
            try {
                val launchesList = repository.getLaunches()
                _launches.value = launchesList
            } catch (e: Exception) {
                Log.d(javaClass.name, e.stackTraceToString())
            }
        }
    }
}
