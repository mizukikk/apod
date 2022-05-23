package com.example.apod.repository

import com.example.apod.api.HttpResult
import com.example.apod.api.obj.Apod
import com.example.apod.api.service.ApodService
import retrofit2.Response
import java.lang.Exception

interface IApodRepository {
    suspend fun getApodList(): HttpResult<Response<List<Apod>>>
}

class ApodRepository(
    private val apodApi: ApodService
) : IApodRepository {

    override suspend fun getApodList(): HttpResult<Response<List<Apod>>> {
        return try {
            val response = apodApi.getApodList().await()
            HttpResult.Success(response)
        } catch (e: Exception) {
            HttpResult.Error(e)
        }
    }
}