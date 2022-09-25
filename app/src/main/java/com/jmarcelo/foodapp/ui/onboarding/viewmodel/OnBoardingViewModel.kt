package com.jmarcelo.foodapp.ui.onboarding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarcelo.foodapp.domain.model.OnBoardingDataDomain
import com.jmarcelo.foodapp.domain.usecase.onboarding.GetInformationOnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getInformationOnBoarding:GetInformationOnBoardingUseCase
): ViewModel(){
    private val _data: MutableLiveData<List<OnBoardingDataDomain>> = MutableLiveData()
    val data: LiveData<List<OnBoardingDataDomain>> get() = _data

    fun getInformationOnbarding(){
       // getInformationOnBoarding = GetInformationOnBoardingUseCase()
        viewModelScope.launch {
            val result= getInformationOnBoarding.getInformationOnBoarding()
            _data.value = result
        }
    }
}