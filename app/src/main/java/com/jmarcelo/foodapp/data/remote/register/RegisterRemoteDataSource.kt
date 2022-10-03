package com.jmarcelo.foodapp.data.remote.register

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jmarcelo.foodapp.common.COLLECTIONSUSER
import com.jmarcelo.foodapp.data.remote.login.model.UserData
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegisterRemoteDataSource @Inject constructor(private val auth: FirebaseAuth,private val firestore: FirebaseFirestore) {

    suspend fun registerUser(userData: UserData):Result<Boolean>{
        return try {
            auth.createUserWithEmailAndPassword(userData.email,userData.password).await()
            Result.success(true)
        }catch (ex:Exception){
            Result.failure(ex)
        }
    }

    suspend fun registerDataUser(hashMap: HashMap<String, String>): Result<Boolean> {
        return try {
            firestore.collection(COLLECTIONSUSER)
                .document(auth.currentUser?.uid.toString())
                .set(hashMap).await()
            Result.success(true)
        }catch (ex : Exception){
            Result.failure(ex)
        }
    }
}