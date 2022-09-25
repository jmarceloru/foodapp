package com.jmarcelo.foodapp.ui.login.viewmodel

import com.jmarcelo.foodapp.domain.model.UserDomain

data class LoginUiState(val errorMail:String = "",val errorPassword:String = "",val error:String="",
    val success:UserDomain? = null)
