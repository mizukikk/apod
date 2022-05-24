package com.example.apod.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.apod.db.entity.ApodEntity

@Dao
interface ApodDao {
    @Insert
    suspend fun insertAll(vararg apodEntities: ApodEntity)

    @Query("SELECT * FROM apod WHERE _id > :lastId LIMIT :limit")
    suspend fun getApodList(lastId: Int, limit: Int = 20): List<ApodEntity>

    @Query("SELECT * FROM apod WHERE _id > :lastId AND favorite = 1 LIMIT :limit")
    suspend fun getFavoriteApodList(lastId: Int, limit: Int = 20): List<ApodEntity>

    @Query("SELECT * FROM apod LIMIT 1")
    suspend fun getFirstApodList(): ApodEntity?

    @Query("UPDATE apod SET favorite = :favorite WHERE _id = :id")
    suspend fun toggleFavorite(favorite: Int, id: Int)

    @Query("SELECT COUNT(_id) FROM apod")
    suspend fun allApodCount(): Int

    @Query("SELECT COUNT(_id) FROM apod WHERE favorite = 1")
    suspend fun favoriteApodCount(): Int
}