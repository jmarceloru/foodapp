package com.jmarcelo.foodapp.data.remote.settings

import com.google.firebase.firestore.FirebaseFirestore
import com.jmarcelo.foodapp.common.*
import com.jmarcelo.foodapp.data.remote.login.model.UserData
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SettingsRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {

    suspend fun getDataUser(uid:String):Result<UserData>{
        return try {
            val result=firestore.collection(COLLECTIONSUSER).document(uid).get().await()
            val userData = UserData(
                result.get(USER) as String,
                result.get(NAME) as String,
                result.get(LASTNAME) as String,
                result.get(PHONE) as String,
                result.get(ADDRESS) as String,
                result.get(EMAIL) as String,
                "")
            Result.success(userData)
        }catch (ex: Exception){
            Result.failure(ex)
        }
    }
}