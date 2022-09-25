package com.jmarcelo.foodapp.domain.usecase.onboarding

import com.jmarcelo.foodapp.data.OnBoardingRepository
import com.jmarcelo.foodapp.data.local.onboarding.model.toDomain
import com.jmarcelo.foodapp.domain.model.OnBoardingDataDomain
import javax.inject.Inject

class GetInformationOnBoardingUseCase @Inject constructor(
    private var repository:OnBoardingRepository
){
     fun getInformationOnBoarding():List<OnBoardingDataDomain>{
       // repository = OnBoardingRepository()
         val result = repository.getInformationOnboarding()
        return result.map { it.toDomain() }
    }
}