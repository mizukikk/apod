package com.example.apod.repository

import com.example.apod.api.HttpResult
import com.example.apod.api.obj.Apod
import com.example.apod.api.service.ApodService
import com.example.apod.db.dao.ApodDao
import com.example.apod.db.entity.ApodEntity
import retrofit2.Response
import java.lang.Exception

interface IApodRepository {
    suspend fun fetchApodList(): HttpResult<Response<List<Apod>>>
    suspend fun isApodDataExist(): Boolean
    suspend fun insertApodList(vararg apodEntities: ApodEntity)
    suspend fun getApodList(lastId: Int, limit: Int): List<ApodEntity>
    suspend fun getFavoriteApodList(lastId: Int, limit: Int): List<ApodEntity>
    suspend fun toggleFavorite(apodEntity: ApodEntity)
    suspend fun allApodCount(): Int
    suspend fun favoriteApodCount(): Int
}

class ApodRepository(
    private val apodApi: ApodService,
    private val apodDao: ApodDao
) : IApodRepository {

    override suspend fun fetchApodList(): HttpResult<Response<List<Apod>>> {
        return try {
            val response = apodApi.fetchApodList().await()
            HttpResult.Success(response)
        } catch (e: Exception) {
            HttpResult.Error(e)
        }
    }

    override suspend fun isApodDataExist() =
        apodDao.getFirstApodList() != null

    override suspend fun insertApodList(vararg apodEntities: ApodEntity) {
        apodDao.insertAll(*apodEntities)
    }


    override suspend fun getApodList(lastId: Int, limit: Int) =
        apodDao.getApodList(lastId, limit)

    override suspend fun getFavoriteApodList(lastId: Int, limit: Int) =
        apodDao.getFavoriteApodList(lastId, limit)

    override suspend fun toggleFavorite(apodEntity: ApodEntity) =
        apodDao.toggleFavorite(
            if (apodEntity.favorite) 0 else 1,
            apodEntity._id!!
        )

    override suspend fun allApodCount() =
        apodDao.allApodCount()

    override suspend fun favoriteApodCount() =
        apodDao.favoriteApodCount()
}