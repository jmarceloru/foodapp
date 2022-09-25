package com.jmarcelo.foodapp.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarcelo.foodapp.domain.usecase.login.AuthenticationUserFirebaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val autehenticationUserFirebase: AuthenticationUserFirebaseUseCase
) : ViewModel() {

    private var _authSuccesfull = MutableLiveData(LoginUiState())
    val authSuccesfull: LiveData<LoginUiState> get() = _authSuccesfull

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun authUser(email: String, password: String) {
        viewModelScope.launch {
            _loading.value = true
            val userDomain = autehenticationUserFirebase(email, password)
            userDomain.onSuccess {
                _authSuccesfull.value = LoginUiState(success = it)
                _loading.value = false
            }.onFailure {
                _authSuccesfull.value = LoginUiState(error = it.message.orEmpty())
                _loading.value = false
            }
        }
    }
}

