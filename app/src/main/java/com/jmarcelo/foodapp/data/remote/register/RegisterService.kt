package com.jmarcelo.foodapp.data.remote.register

import com.google.firebase.auth.FirebaseAuth
import com.jmarcelo.foodapp.data.remote.login.model.UserData
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegisterService @Inject constructor(private val auth: FirebaseAuth) {

    suspend fun registerUser(email:String,password:String):Result<UserData>{
        return try {
            val task = auth.createUserWithEmailAndPassword(email,password).await()
            Result.success(UserData(email,password))
        }catch (ex:Exception){
            Result.failure(ex)
        }
    }
}