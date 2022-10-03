package com.jmarcelo.foodapp.data


import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.jmarcelo.foodapp.data.remote.login.LoginRemoteDataSource
import javax.inject.Inject


class LoginRepository @Inject constructor(private val loginRemoteDataSource: LoginRemoteDataSource) {

    suspend fun authenticationUserFirebase(email:String,password:String):Result<Boolean>{
        return loginRemoteDataSource.autheticationUserFirebase(email,password)
    }

    suspend fun authenticationUserGoogle(task: Task<GoogleSignInAccount>):Result<Boolean>{
        return loginRemoteDataSource.autheticacionUserGoogle(task)
    }
}