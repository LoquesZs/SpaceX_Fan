package by.loqueszs.spacexfan.presentation.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.loqueszs.spacexfan.core.database.entities.RocketEntity
import by.loqueszs.spacexfan.domain.SpaceXFanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: SpaceXFanRepository
) : ViewModel() {

    private val _favorites by lazy {
        MutableLiveData<List<RocketEntity>>()
    }
    val favorites: LiveData<List<RocketEntity>>
        get() = _favorites

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    init {
        repository.getFavorites()
            .subscribe(
                { rockets ->
                    _favorites.value = rockets
                },
                {
                    Log.d(javaClass.simpleName, it.stackTraceToString())
                }
            ).addTo(compositeDisposable)
    }

    fun deleteFromFavorites(id: String) {
        repository.deleteFromFavorites(id)
            .subscribe {
                Log.d(javaClass.simpleName, "Deleted successfully")
            }
            .addTo(compositeDisposable)
    }
}

