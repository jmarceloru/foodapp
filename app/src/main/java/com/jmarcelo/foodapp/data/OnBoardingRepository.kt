package com.jmarcelo.foodapp.data

import com.jmarcelo.foodapp.data.local.onboarding.OnBoardingDataProvider
import com.jmarcelo.foodapp.data.local.onboarding.model.OnBoardingData
import javax.inject.Inject

class OnBoardingRepository @Inject constructor(
    private val onBoardingDataProvider: OnBoardingDataProvider
){
    fun getInformationOnboarding():List<OnBoardingData>{
       // onBoardingDataProvider = OnBoardingDataProvider()
        return onBoardingDataProvider.onBoardingDataList
    }
}