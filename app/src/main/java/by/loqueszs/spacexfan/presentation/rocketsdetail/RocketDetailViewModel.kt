package by.loqueszs.spacexfan.presentation.rocketsdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import by.loqueszs.spacexfan.core.database.entities.RocketEntity
import by.loqueszs.spacexfan.core.network.models.rockets.Rocket
import by.loqueszs.spacexfan.domain.SpaceXFanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@HiltViewModel
class RocketDetailViewModel @Inject constructor(
    private val repository: SpaceXFanRepository
) : ViewModel() {

    private var favorites: List<RocketEntity> = emptyList()

    private val compositeDisposable = CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    init {
        repository
            .getFavorites()
            .subscribe(
                { rockets ->
                    favorites = rockets
                },
                {
                    Log.d(javaClass.simpleName, it.stackTraceToString())
                }
            ).addTo(compositeDisposable)
    }

    fun getRocket(id: String): Single<Rocket> {
        return repository.getRocketByID(id)
    }

    fun isFavorite(id: String): Boolean {
        return favorites.any { it.id == id }
    }

    fun addToFavorites(rocketEntity: RocketEntity) {
        repository.addToFavorites(rocketEntity)
            .subscribe {
                Log.d(javaClass.simpleName, "Added to favorites")
            }
            .addTo(compositeDisposable)
    }

    fun deleteFromFavorites(id: String) {
        repository.deleteFromFavorites(id)
            .subscribe {
                Log.d(javaClass.simpleName, "Deleted from favorites")
            }
            .addTo(compositeDisposable)
    }
}