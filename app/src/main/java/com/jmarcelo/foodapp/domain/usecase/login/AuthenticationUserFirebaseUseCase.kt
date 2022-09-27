package com.jmarcelo.foodapp.domain.usecase.login

import android.util.Patterns
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.jmarcelo.foodapp.data.LoginRepository
import com.jmarcelo.foodapp.data.remote.login.model.UserData
import com.jmarcelo.foodapp.data.remote.login.model.toDomain
import com.jmarcelo.foodapp.domain.model.UserDomain
import java.lang.Exception
import javax.inject.Inject

class AuthenticationUserFirebaseUseCase @Inject constructor(private val loginRepository: LoginRepository){

    suspend fun loginEmailWithPasswordFirebase(email:String, password:String):Result<Boolean>{
        val emailPattern = Patterns.EMAIL_ADDRESS
        val result : Result<Boolean> = if (emailPattern.matcher(email).matches()){
            if (password.length>5){
                loginRepository.authenticationUserFirebase(email,password)
            }else{
                Result.failure(Exception("Password incorrecto, minimo 6 caracteres"))
            }
        }else{
            Result.failure(Exception("Email Invalido"))
        }
        return result
    }

    suspend fun loginGoogle(task: Task<GoogleSignInAccount>):Result<Boolean>{
       return loginRepository.authenticationUserGoogle(task)
    }
}