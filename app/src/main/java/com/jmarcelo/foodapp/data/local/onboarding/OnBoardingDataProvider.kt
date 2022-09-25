package com.jmarcelo.foodapp.data.local.onboarding

import com.jmarcelo.foodapp.R
import com.jmarcelo.foodapp.data.local.onboarding.model.OnBoardingData
import javax.inject.Inject

class OnBoardingDataProvider @Inject constructor() {

     val onBoardingDataList = listOf(
        OnBoardingData(
            "Title 1",
            "Esta es una descripcion del titulo 1",
            R.drawable.oboarding1),
        OnBoardingData(
            "Title 2",
            "Esta es una descripcion del titulo 2",
            R.drawable.oboarding2),
        OnBoardingData(
            "Title 3",
            "Esta es una descripcion del titulo 3",
            R.drawable.oboarding3)
    )
}