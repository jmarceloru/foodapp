package com.jmarcelo.foodapp.data.remote.login.model

import com.jmarcelo.foodapp.domain.model.UserDomain

data class UserData(val user:String,val name:String,val lastName:String,
                    val phone:String,val address:String,val email:String,val password:String)

fun UserData.toDomain() = UserDomain(user, name, lastName, phone, address, email, password)

fun UserDomain.toData() = UserData(user, name, lastName, phone, address, email, password)