package com.example.apod.main.apod.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.apod.db.entity.ApodEntity

class ApodDiffCall(
    private val oList: List<ApodEntity>,
    private val nList: List<ApodEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oList.size
    override fun getNewListSize() = nList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newId = nList[newItemPosition]._id
        val oldId = oList[oldItemPosition]._id
        return newId != null && oldId != null && newId == oldId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newData = nList[newItemPosition]
        val oldData = oList[oldItemPosition]
        return newData == oldData
    }
}