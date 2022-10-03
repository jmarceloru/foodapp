package com.jmarcelo.foodapp.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
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

    fun authUser(email: String, password: String) {
        viewModelScope.launch {
            _authSuccesfull.value = LoginUiState(loadingProgressBar = true)
            val userDomain = autehenticationUserFirebase.loginEmailWithPasswordFirebase(email, password)
            userDomain.onSuccess {
                _authSuccesfull.value = LoginUiState(success = it, loadingProgressBar = false)
            }.onFailure {
                _authSuccesfull.value = LoginUiState(error = it.message.orEmpty(), loadingProgressBar = false)
            }
        }
    }

    fun authUserGoogle(task: Task<GoogleSignInAccount>){
        viewModelScope.launch {
            _authSuccesfull.value = LoginUiState(loadingProgressBar = true)
            val userFirebase = autehenticationUserFirebase.loginGoogle(task)
            userFirebase.onSuccess {
                _authSuccesfull.value = LoginUiState(success = it, loadingProgressBar = false)
            }.onFailure {
                _authSuccesfull.value = LoginUiState(error = it.message.orEmpty(), loadingProgressBar = false)
            }
        }
    }
}

