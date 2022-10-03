package com.jmarcelo.foodapp.domain.usecase.register

import android.util.Patterns
import com.jmarcelo.foodapp.common.*
import com.jmarcelo.foodapp.data.LoginRepository
import com.jmarcelo.foodapp.data.RegisterReporsitory
import com.jmarcelo.foodapp.data.remote.login.model.UserData
import com.jmarcelo.foodapp.data.remote.login.model.toData
import com.jmarcelo.foodapp.data.remote.login.model.toDomain
import com.jmarcelo.foodapp.domain.model.UserDomain
import java.lang.Exception
import javax.inject.Inject

class RegisterUserFirebaseUseCase @Inject constructor(private val registerReporsitory: RegisterReporsitory) {

     suspend fun registerUser(userDomain: UserDomain):Result<Boolean>{
         val emailPattern = Patterns.EMAIL_ADDRESS
         val result : Result<Boolean> = if (emailPattern.matcher(userDomain.email).matches()){
             if (userDomain.password.length>5){
                 val userData = userDomain.toData()
                 registerReporsitory.registerFirebaseUser(userData)
             }else{
                 Result.failure(Exception("Password incorrecto, minimo 6 caracteres"))
             }
         }else{
             Result.failure(Exception("Email Invalido"))
         }
         return result
     }

    suspend fun registerUserData(userDomain: UserDomain):Result<Boolean>{
        val hashMap = hashMapOf(
            USER to userDomain.user,
            NAME to userDomain.name,
            LASTNAME to userDomain.lastName,
            PHONE to userDomain.phone,
            ADDRESS to userDomain.address,
            EMAIL to userDomain.email)
        return registerReporsitory.registerDataFirebaseUser(hashMap)
    }
}