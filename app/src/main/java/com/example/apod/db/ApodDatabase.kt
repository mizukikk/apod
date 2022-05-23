package com.example.apod.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apod.db.dao.ApodDao
import com.example.apod.db.entity.ApodEntity

@Database(
    entities = [
        ApodEntity::class
    ],
    version = 1
)
abstract class ApodDatabase : RoomDatabase() {
    abstract fun apodDao(): ApodDao

    companion object {
        const val MLTD_DB_NAME = "mltdSup.db"
    }
}