package by.loqueszs.spacexfan.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class UIState {
    data object Loading : UIState()
    data object Failure : UIState()
    data object Success : UIState()
}

@HiltViewModel
class AuthorizationViewModel @Inject constructor() : ViewModel() {

    private val _result by lazy {
        MutableLiveData<UIState?>(null)
    }
    val result: LiveData<UIState?> = _result

    var login: String? = null
        private set

    var password: String? = null
        private set

    fun setLogin(value: String) {
        login = value
    }

    fun setPassword(value: String) {
        password = value
    }

    fun auth(login: String, password: String) {
        if (login.isNullOrEmpty() || password.isNullOrEmpty()) {
            _result.value = UIState.Failure
            return
        }

        _result.value = UIState.Loading

        Firebase.auth.signInWithEmailAndPassword(login, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _result.value = UIState.Success
            } else {
                _result.value = UIState.Failure
            }
        }
    }
}
