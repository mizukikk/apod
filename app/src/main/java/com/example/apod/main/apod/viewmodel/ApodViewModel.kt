package com.example.apod.main.apod.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apod.api.HttpResult
import com.example.apod.api.obj.Apod
import com.example.apod.db.entity.ApodEntity
import com.example.apod.repository.IApodRepository
import kotlinx.coroutines.launch

class ApodViewModel(private val apodRepository: IApodRepository) : ViewModel() {

    val apodListLiveData = MutableLiveData<List<ApodEntity>>()

    fun getApodList() {
        viewModelScope.launch {
            val result = apodRepository.getApodList()
            when (result) {
                is HttpResult.Success -> {
                    if (result.response.isSuccessful) {
                        val apodList = result.response.body()!!
                            .map {
                                ApodEntity(it)
                            }
                        apodListLiveData.value = apodList.take(20)
                    } else {

                    }
                }
                is HttpResult.Error -> {

                }
            }
        }
    }

}