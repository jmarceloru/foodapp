package com.jmarcelo.foodapp.data.remote.login.model

import com.jmarcelo.foodapp.domain.model.UserDomain

data class UserData(val email:String,val password:String)

fun UserData.toDomain() = UserDomain(email,password)
