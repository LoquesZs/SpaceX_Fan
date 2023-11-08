package by.loqueszs.spacexfan.presentation.rockets

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.loqueszs.spacexfan.core.database.entities.RocketEntity
import by.loqueszs.spacexfan.core.network.models.rockets.Rocket
import by.loqueszs.spacexfan.domain.SpaceXFanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
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

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    init {
        _result.addSource(rockets) { rocketsList ->
            Log.d("Huj", "huj1")
            _result.value = rocketsList.map { rocket ->
                Pair(
                    rocket,
                    favorites.value?.any { it.id == rocket.id } ?: false
                )
            }
        }
        _result.addSource(favorites) { favoritesList ->
            Log.d("Huj", "huj2")
            _result.value = rockets.value?.map { rocket ->
                Pair(
                    rocket,
                    favoritesList.any { it.id == rocket.id }
                )
            }
        }
        repository.getRockets()
            .subscribe(
                { rocketsList ->
                    _rockets.value = rocketsList
                },
                {
                    Log.d("getRockets()", it.message.orEmpty())
                }
            )
            .addTo(compositeDisposable)

        repository.getFavorites()
            .subscribe(
                { favoritesList ->
                    _favorites.value = favoritesList
                },
                {
                    Log.d("getFavorites()", it.stackTraceToString())
                }
            )
            .addTo(compositeDisposable)
    }

    fun addToFavorites(rocket: RocketEntity) {
        repository.addToFavorites(rocket).subscribe().addTo(compositeDisposable)
    }

    fun removeFromFavorites(id: String) {
        repository.deleteFromFavorites(id).subscribe().addTo(compositeDisposable)
    }
}
