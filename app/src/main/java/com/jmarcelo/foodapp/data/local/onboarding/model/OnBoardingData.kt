package com.jmarcelo.foodapp.data.local.onboarding.model

import com.jmarcelo.foodapp.domain.model.OnBoardingDataDomain

data class OnBoardingData(val title:String, val description:String, val imageURL:Int)

fun OnBoardingData.toDomain() = OnBoardingDataDomain(title,description,imageURL)