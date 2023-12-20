package by.loqueszs.spacexfan.presentation.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.loqueszs.spacexfan.core.database.entities.RocketEntity
import by.loqueszs.spacexfan.domain.SpaceXFanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: SpaceXFanRepository
) : ViewModel() {

    private val _favorites by lazy {
        MutableLiveData<List<RocketEntity>>()
    }
    val favorites: LiveData<List<RocketEntity>>
        get() = _favorites

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites()
                .distinctUntilChanged()
                .catch {
                    Log.d(javaClass.simpleName, it.stackTraceToString())
                    emit(emptyList())
                }
                .collect { rockets ->
                    _favorites.postValue(rockets)
                }
        }
    }

    fun deleteFromFavorites(id: String) {
        viewModelScope.launch {
            try {
                repository.deleteFromFavorites(id)
                Log.d(javaClass.simpleName, "Deleted successfully")
            } catch (e: Exception) {
                Log.d(javaClass.simpleName, "Delete failure: ${e.printStackTrace()}")
            }
        }
    }
}
