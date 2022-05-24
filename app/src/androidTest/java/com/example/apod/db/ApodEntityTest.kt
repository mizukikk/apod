package com.example.apod.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.apod.db.dao.ApodDao
import com.example.apod.db.entity.ApodEntity
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApodEntityTest {
    private lateinit var apodDao: ApodDao
    private lateinit var db: ApodDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            ApodDatabase::class.java
        ).build()
        apodDao = db.apodDao()
    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testWriteApodAndRead() {
        val testData = ApodEntity(
            "apodSite",
            "copyright",
            "date",
            "description",
            "hdurl",
            "mediaType",
            "title",
            "url"
        )

        var apodEntity: ApodEntity?
        runBlocking {
            apodDao.insertAll(testData)
            apodEntity = apodDao.getFirstApodList()
        }
        assertNotNull(apodEntity)
        assertEquals("apodSite", apodEntity!!.apodSite)
        assertEquals("copyright", apodEntity!!.copyright)
        assertEquals("date", apodEntity!!.date)
        assertEquals("description", apodEntity!!.description)
        assertEquals("hdurl", apodEntity!!.hdUrl)
        assertEquals("mediaType", apodEntity!!.mediaType)
        assertEquals("title", apodEntity!!.title)
        assertEquals("url", apodEntity!!.url)
    }

}