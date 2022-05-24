package com.example.apod.main.apod.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apod.api.HttpResult
import com.example.apod.component.livedate.SingleLiveEvent
import com.example.apod.db.entity.ApodEntity
import com.example.apod.repository.IApodRepository
import kotlinx.coroutines.launch

class ApodListViewModel(private val apodRepository: IApodRepository) : ViewModel() {

    companion object {
        private const val PAGINATION_COUNT = 30
    }

    val apodListLiveData = MutableLiveData<List<ApodEntity>>()
    val progressEvent = SingleLiveEvent<Boolean>()

    fun getApodList() {
        viewModelScope.launch {
            if (apodRepository.isApodDataExist()) {
                val apodList = apodRepository.getApodList(0, PAGINATION_COUNT)
                postApodList(apodList)
            } else {
                fetchApodList()
            }
        }
    }

    private suspend fun fetchApodList() {
        progressEvent.value = true
        val result = apodRepository.fetchApodList()
        when (result) {
            is HttpResult.Success -> {
                if (result.response.isSuccessful) {
                    val apodList = result.response.body()!!
                        .map {
                            ApodEntity(it)
                        }
                    apodRepository.insertApodList(*apodList.toTypedArray())
                    postApodList(apodList.take(20))
                }
                progressEvent.value = false
            }
            is HttpResult.Error -> {
                progressEvent.value = false
            }
        }
    }

    private fun postApodList(apodList: List<ApodEntity>) {
        val currentList = apodListLiveData.value
        if (currentList == null) {
            apodListLiveData.value = apodList
        } else {
            val newList = mutableListOf<ApodEntity>()
            newList.addAll(currentList)
            newList.addAll(apodList)
        }
    }

}