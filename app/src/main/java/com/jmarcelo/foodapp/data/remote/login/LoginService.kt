package com.jmarcelo.foodapp.data.remote.login

import com.google.firebase.auth.FirebaseAuth
import com.jmarcelo.foodapp.data.remote.login.model.UserData
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginService @Inject constructor(private val auth: FirebaseAuth) {

    suspend fun autheticationUser(email:String,password:String):Result<UserData>{
        return try {
            val task = auth.signInWithEmailAndPassword(email,password).await()
            Result.success(UserData(email,password))
        }catch (ex:Exception){
            Result.failure(ex)
        }
    }
}