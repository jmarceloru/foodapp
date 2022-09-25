package com.jmarcelo.foodapp.data

import com.jmarcelo.foodapp.data.remote.login.model.UserData
import com.jmarcelo.foodapp.data.remote.register.RegisterService
import javax.inject.Inject

class RegisterReporsitory  @Inject constructor(private val registerService: RegisterService)  {

    suspend fun registerFirebaseUser(email:String,password:String):Result<UserData>{
        return registerService.registerUser(email,password)
    }
}