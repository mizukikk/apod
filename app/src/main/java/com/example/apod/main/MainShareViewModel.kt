package com.example.apod.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apod.main.apod.adapter.ApodPageAdapter
import com.example.apod.repository.IApodRepository
import kotlinx.coroutines.launch

class MainShareViewModel(private val apodRepository: IApodRepository) : ViewModel() {

    val apodCountLiveData = MediatorLiveData<HashMap<ApodPageAdapter.Type, Int>>()

    fun updateTabCount(type: ApodPageAdapter.Type) {
        viewModelScope.launch {
            val apodCountMap = apodCountLiveData.value ?: hashMapOf()
            val currentCount = if (apodCountMap.containsKey(type)) {
                apodCountMap[type] ?: 0
            } else {
                0
            }
            val newCount = when (type) {
                ApodPageAdapter.Type.All ->
                    apodRepository.allApodCount()

                ApodPageAdapter.Type.Favorite ->
                    apodRepository.favoriteApodCount()
            }
            if (currentCount != newCount) {
                apodCountMap[type] = newCount
                apodCountLiveData.value = apodCountMap
            }

        }
    }
}