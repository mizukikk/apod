package com.example.apod.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.apod.db.dao.ApodDao
import com.example.apod.db.entity.ApodEntity

@Database(
    entities = [
        ApodEntity::class
    ],
    version = 2
)
abstract class ApodDatabase : RoomDatabase() {
    abstract fun apodDao(): ApodDao

    companion object {
        const val DB_NAME = "apod.db"
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE apod ADD COLUMN favorite INTEGER NOT NULL DEFAULT 0")
            }
        }
    }
}