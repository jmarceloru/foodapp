package com.jmarcelo.foodapp.data


import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.jmarcelo.foodapp.data.remote.login.LoginService
import com.jmarcelo.foodapp.data.remote.login.model.UserData
import javax.inject.Inject


class LoginRepository @Inject constructor(private val loginService: LoginService) {

    suspend fun authenticationUserFirebase(email:String,password:String):Result<Boolean>{
        return loginService.autheticationUserFirebase(email,password)
    }

    suspend fun authenticationUserGoogle(task: Task<GoogleSignInAccount>):Result<Boolean>{
        return loginService.autheticacionUserGoogle(task)
    }
}