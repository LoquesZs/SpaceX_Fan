package by.loqueszs.spacexfan.presentation.rockets

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.loqueszs.spacexfan.core.database.entities.RocketEntity
import by.loqueszs.spacexfan.core.network.models.rockets.Rocket
import by.loqueszs.spacexfan.domain.SpaceXFanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RocketsViewModel @Inject constructor(
    private val repository: SpaceXFanRepository
) : ViewModel() {

    private val _rockets by lazy {
        MutableLiveData<List<Rocket>>()
    }

    val rockets: LiveData<List<Rocket>>
        get() = _rockets

    private val _favorites by lazy {
        MutableLiveData<List<RocketEntity>>()
    }

    val favorites: LiveData<List<RocketEntity>>
        get() = _favorites

    private val _result by lazy {
        MediatorLiveData<List<Pair<Rocket, Boolean>>>()
    }

    val result: LiveData<List<Pair<Rocket, Boolean>>>
        get() = _result

    val _error by lazy {
        MutableLiveData<Boolean>(false)
    }

    val error: LiveData<Boolean>
        get() = _error

    init {
        _result.addSource(rockets) { rocketsList ->
            _result.value = rocketsList.map { rocket ->
                Pair(
                    rocket,
                    favorites.value?.any { it.id == rocket.id } ?: false
                )
            }
        }
        _result.addSource(favorites) { favoritesList ->
            _result.value = rockets.value?.map { rocket ->
                Pair(
                    rocket,
                    favoritesList.any { it.id == rocket.id }
                )
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val rocketsList = repository.getRockets()
                withContext(Dispatchers.Main) {
                    _rockets.value = rocketsList
                }
            } catch (e: Exception) {
                Log.d("getRockets()", e.stackTraceToString())
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites()
                .distinctUntilChanged()
                .catch {
                    Log.d("getFavorites()", it.stackTraceToString())
                    emit(emptyList())
                }
                .collect { favoritesList ->
                    _favorites.postValue(favoritesList)
                }
        }
    }

    fun addToFavorites(rocket: RocketEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.addToFavorites(rocket)
            } catch (e: Exception) {
                Log.d("addToFavorites()", e.stackTraceToString())
            }
        }
    }

    fun removeFromFavorites(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteFromFavorites(id)
            } catch (e: Exception) {
                Log.d("removeFromFavorites()", e.stackTraceToString())
            }
        }
    }
}
