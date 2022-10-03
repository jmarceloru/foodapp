package com.jmarcelo.foodapp.domain.usecase.settings

import com.jmarcelo.foodapp.data.SettingsRepository
import com.jmarcelo.foodapp.data.remote.login.model.toDomain
import com.jmarcelo.foodapp.domain.model.UserDomain
import javax.inject.Inject

class GetDataUserUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {

    suspend fun getDataUser(uid:String):Result<UserDomain>{
        return settingsRepository.getDataUser(uid).map { it.toDomain() }
    }
}