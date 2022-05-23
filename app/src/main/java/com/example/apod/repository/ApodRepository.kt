package com.example.apod.repository

import com.example.apod.api.HttpResult
import com.example.apod.api.obj.Apod
import com.example.apod.api.service.ApodService
import com.example.apod.db.dao.ApodDao
import com.example.apod.db.entity.ApodEntity
import retrofit2.Response
import java.lang.Exception

interface IApodRepository {
    suspend fun getApodList(): HttpResult<Response<List<Apod>>>
    suspend fun insertApodList(vararg apodEntities: ApodEntity)
}

class ApodRepository(
    private val apodApi: ApodService,
    private val apodDao: ApodDao
) : IApodRepository {

    override suspend fun getApodList(): HttpResult<Response<List<Apod>>> {
        return try {
            val response = apodApi.getApodList().await()
            HttpResult.Success(response)
        } catch (e: Exception) {
            HttpResult.Error(e)
        }
    }

    override suspend fun insertApodList(vararg apodEntities: ApodEntity) {
        apodDao.insertAll(*apodEntities)
    }
}