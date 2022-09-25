package com.jmarcelo.foodapp.data


import com.jmarcelo.foodapp.data.remote.login.LoginService
import com.jmarcelo.foodapp.data.remote.login.model.UserData
import javax.inject.Inject


class LoginRepository @Inject constructor(private val loginService: LoginService) {

    suspend fun authenticationUserFirebase(email:String,password:String):Result<UserData>{
        return loginService.autheticationUser(email,password)
    }
}