package by.loqueszs.spacexfan.presentation.rocketsdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.loqueszs.spacexfan.core.database.entities.RocketEntity
import by.loqueszs.spacexfan.core.network.models.rockets.Rocket
import by.loqueszs.spacexfan.domain.SpaceXFanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class RocketDetailViewModel @Inject constructor(
    private val repository: SpaceXFanRepository
) : ViewModel() {

    private var favorites: List<RocketEntity> = emptyList()

    private val _rocket by lazy {
        MutableLiveData<Rocket>()
    }

    val rocket: LiveData<Rocket>
        get() = _rocket

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites()
                .distinctUntilChanged()
                .catch {
                    Log.d(javaClass.simpleName, it.stackTraceToString())
                }
                .collect { rockets ->
                    favorites = rockets
                }
        }
    }

    fun getRocket(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getRocketByID(id)
            withContext(Dispatchers.Main) {
                _rocket.value = data
            }
        }
    }

    fun isFavorite(id: String): Boolean {
        return favorites.any { it.id == id }
    }

    fun addToFavorites(rocketEntity: RocketEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToFavorites(rocketEntity)
        }
    }

    fun deleteFromFavorites(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFromFavorites(id)
        }
    }
}
