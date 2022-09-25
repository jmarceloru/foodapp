package com.jmarcelo.foodapp.domain.usecase.login

import android.util.Patterns
import com.jmarcelo.foodapp.data.LoginRepository
import com.jmarcelo.foodapp.data.remote.login.model.UserData
import com.jmarcelo.foodapp.data.remote.login.model.toDomain
import com.jmarcelo.foodapp.domain.model.UserDomain
import java.lang.Exception
import javax.inject.Inject

class AuthenticationUserFirebaseUseCase @Inject constructor(private val loginRepository: LoginRepository){

    suspend operator fun invoke(email:String, password:String):Result<UserDomain>{
        val emailPattern = Patterns.EMAIL_ADDRESS
        val result : Result<UserData> = if (emailPattern.matcher(email).matches()){
            if (password.length>5){
                loginRepository.authenticationUserFirebase(email,password)
            }else{
                Result.failure(Exception("Password incorrecto, minimo 6 caracteres"))
            }
        }else{
            Result.failure(Exception("Email Invalido"))
        }
        return result.map { it.toDomain() }
    }
}