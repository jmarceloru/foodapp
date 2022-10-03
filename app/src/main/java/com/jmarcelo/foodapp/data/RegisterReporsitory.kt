package com.jmarcelo.foodapp.data

import com.jmarcelo.foodapp.data.remote.login.model.UserData
import com.jmarcelo.foodapp.data.remote.register.RegisterRemoteDataSource
import javax.inject.Inject

class RegisterReporsitory  @Inject constructor(private val registerRemoteDataSource: RegisterRemoteDataSource)  {

    suspend fun registerFirebaseUser(userData: UserData):Result<Boolean>{
        return registerRemoteDataSource.registerUser(userData)
    }

    suspend fun registerDataFirebaseUser(hashMap: HashMap<String, String>):Result<Boolean>{
        return registerRemoteDataSource.registerDataUser(hashMap)
    }
}