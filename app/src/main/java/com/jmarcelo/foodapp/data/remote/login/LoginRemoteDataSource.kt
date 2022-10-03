package com.jmarcelo.foodapp.data.remote.login

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.jmarcelo.foodapp.data.remote.login.model.UserData
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(private val auth: FirebaseAuth) {

    suspend fun autheticationUserFirebase(email:String,password:String):Result<Boolean>{
        return try {
            val task = auth.signInWithEmailAndPassword(email,password).await()
            Result.success(true)
        }catch (ex:Exception){
            Result.failure(ex)
        }
    }

    suspend fun autheticacionUserGoogle(task: Task<GoogleSignInAccount>): Result<Boolean> {
        return try {
            val account: GoogleSignInAccount? = task.result
            val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
            auth.signInWithCredential(credential).await()
            Result.success(true)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}