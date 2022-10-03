package com.jmarcelo.foodapp.ui.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarcelo.foodapp.domain.model.UserDomain
import com.jmarcelo.foodapp.domain.usecase.register.RegisterUserFirebaseUseCase
import com.jmarcelo.foodapp.ui.login.viewmodel.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserFirebaseUseCase: RegisterUserFirebaseUseCase
) :ViewModel(){

    private var _registerUserSuccess=MutableLiveData(LoginUiState())
    val registerUserSucces: LiveData<LoginUiState> get() = _registerUserSuccess


    fun registerUser(userDomain: UserDomain){
        viewModelScope.launch {
            _registerUserSuccess.value = LoginUiState(loadingProgressBar = true)
            val user = registerUserFirebaseUseCase.registerUser(userDomain)
            user.onSuccess {
                val data = registerUserFirebaseUseCase.registerUserData(userDomain)
                data.onSuccess {
                    _registerUserSuccess.value = LoginUiState(success = it, loadingProgressBar = false)
                }.onFailure {
                    _registerUserSuccess.value = LoginUiState(error = it.message.orEmpty(), loadingProgressBar = false)
                }
            }.onFailure {
                _registerUserSuccess.value = LoginUiState(error = it.message.orEmpty(), loadingProgressBar = false)
            }
        }
    }

}