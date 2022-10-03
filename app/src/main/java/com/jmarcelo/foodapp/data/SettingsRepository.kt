package com.jmarcelo.foodapp.data

import com.jmarcelo.foodapp.data.remote.login.model.UserData
import com.jmarcelo.foodapp.data.remote.settings.SettingsRemoteDataSource
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val settingsRemoteDataSource: SettingsRemoteDataSource) {

    suspend fun getDataUser(uid:String):Result<UserData>{
        return settingsRemoteDataSource.getDataUser(uid)
    }
}