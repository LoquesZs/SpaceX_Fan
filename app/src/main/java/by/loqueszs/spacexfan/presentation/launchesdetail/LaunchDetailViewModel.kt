package by.loqueszs.spacexfan.presentation.launchesdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.loqueszs.spacexfan.core.network.models.launches.Launch
import by.loqueszs.spacexfan.domain.SpaceXFanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class LaunchDetailViewModel @Inject constructor(
    private val repository: SpaceXFanRepository
) : ViewModel() {

    private val _launch by lazy {
        MutableLiveData<Launch>()
    }
    val launch: LiveData<Launch>
        get() = _launch

    fun getLaunch(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = repository.getLaunchByID(id)
                _launch.postValue(data)
            } catch (e: Exception) {
                Log.d(javaClass.simpleName, e.stackTraceToString())
            }
        }
    }
}
