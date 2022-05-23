package com.example.apod.repository

import TestFileUtils
import com.example.apod.api.HttpResult
import com.example.apod.api.MockInterceptor
import com.example.apod.api.MockResponse
import com.example.apod.api.NetworkService
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking

class ApodRepositoryTest : TestCase() {

    private lateinit var mockInterceptor: MockInterceptor
    private lateinit var networkService: NetworkService
    private lateinit var apodRepository: IApodRepository

    override fun setUp() {
        mockInterceptor = MockInterceptor()
        networkService = NetworkService(mockInterceptor)
        apodRepository = ApodRepository(networkService.apodApi)
    }

    fun testGetApodList() {
        mockInterceptor.setListener(object : MockInterceptor.MockInterceptorListener {
            override fun setApiResponse(url: String): MockResponse {
                val state = 200
                val responseText = TestFileUtils.readText("api/FakeApodList.json")
                return MockResponse(state, responseText)
            }
        })

        val result = runBlocking {
            apodRepository.getApodList() as HttpResult.Success
        }
        assertTrue(result.response.isSuccessful)
        val response = result.response.body()!!
        assertEquals(1, response.size)
        assertEquals(
            "The past year was extraordinary for the discovery of extraterrestrial fountains and flows -- some offering new potential in the search for liquid water and the origin of life beyond planet Earth.. Increased evidence was uncovered that fountains spurt not only from Saturn's moon Enceladus, but from the dunes of Mars as well. Lakes were found on Saturn's moon Titan, and the residual of a flowing liquid was discovered on the walls of Martian craters. The diverse Solar System fluidity may involve forms of slushy water-ice, methane, or sublimating carbon dioxide. Pictured above, the light-colored path below the image center is hypothesized to have been created sometime in just the past few years by liquid water flowing across the surface of Mars.",
            response[0].description
        )
        assertEquals("MGS, MSSS, JPL, NASA", response[0].copyright)
        assertEquals("A Year of Extraterrestrial Fountains and Flows", response[0].title)
        assertEquals("https://apod.nasa.gov/apod/image/0612/flow_mgs.jpg", response[0].url)
        assertEquals("2006-12-31", response[0].date)
        assertEquals("image", response[0].mediaType)
        assertEquals("https://apod.nasa.gov/apod/image/0612/flow_mgs_big.jpg", response[0].hdurl)
    }

}