package com.example.apod.main.apod.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apod.api.HttpResult
import com.example.apod.component.livedate.SingleLiveEvent
import com.example.apod.db.entity.ApodEntity
import com.example.apod.main.apod.adapter.ApodPageAdapter
import com.example.apod.repository.IApodRepository
import kotlinx.coroutines.launch

class ApodListViewModel(private val apodRepository: IApodRepository) : ViewModel() {

    companion object {
        private const val PAGINATION_COUNT = 30
    }

    val apodListLiveData = MutableLiveData<List<ApodEntity>>()
    val progressEvent = SingleLiveEvent<Boolean>()
    private var currentLastId = -1
    private var isLoadNext = false

    fun loadFirstApodList(type: ApodPageAdapter.Type) {
        if (apodListLiveData.value == null)
            viewModelScope.launch {
                if (apodRepository.isApodDataExist()) {
                    loadApodList(0, type)
                } else {
                    fetchApodList(type)
                }
            }
    }

    private suspend fun loadApodList(lastId: Int, type: ApodPageAdapter.Type) {
        val apodList = when (type) {
            ApodPageAdapter.Type.All ->
                apodRepository.getApodList(lastId, PAGINATION_COUNT)
            ApodPageAdapter.Type.Favorite ->
                apodRepository.getFavoriteApodList(lastId, PAGINATION_COUNT)
        }
        postApodList(apodList)
    }

    fun refreshApodList(type: ApodPageAdapter.Type, itemCount: Int) {
        viewModelScope.launch {
            val limit = if (itemCount <= PAGINATION_COUNT) PAGINATION_COUNT else itemCount
            val apodList = when (type) {
                ApodPageAdapter.Type.All ->
                    apodRepository.getApodList(0, limit)
                ApodPageAdapter.Type.Favorite ->
                    apodRepository.getFavoriteApodList(0, limit)
            }
            apodListLiveData.value = apodList
        }
    }

    fun loadNextApodList(lastId: Int, type: ApodPageAdapter.Type) {
        //????????????????????????id
        if (lastId <= 0)
            return
        //????????????????????????
        if (isLoadNext)
            return
        //?????????????????????
        if (currentLastId == lastId)
            return
        isLoadNext = true
        currentLastId = lastId
        viewModelScope.launch {
            loadApodList(lastId, type)
            isLoadNext = false
        }

    }

    private suspend fun fetchApodList(type: ApodPageAdapter.Type) {
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
                    loadApodList(0, type)
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
            apodListLiveData.value = newList
        }
    }

    fun toggleFavorite(apodEntity: ApodEntity, type: ApodPageAdapter.Type, itemCount: Int) {
        viewModelScope.launch {
            apodRepository.toggleFavorite(apodEntity)
            refreshApodList(type, itemCount)
        }
    }

}