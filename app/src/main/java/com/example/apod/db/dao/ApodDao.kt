package com.example.apod.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.apod.db.entity.ApodEntity

@Dao
interface ApodDao {
    @Insert
    fun insertAll(vararg apodEntities: ApodEntity)

    @Query("SELECT * FROM apod WHERE _id >= :id LIMIT :limit")
    fun getApodList(id: Int, limit: Int = 20): List<ApodEntity>

    @Query("SELECT * FROM apod LIMIT 1")
    fun getFirstApodList(): ApodEntity?
}