package com.example.apod.main.apod.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.example.apod.base.BaseAdapter
import com.example.apod.base.BaseViewHolder
import com.example.apod.databinding.ItemApodBinding
import com.example.apod.db.entity.ApodEntity

class ApodAdapter : BaseAdapter<ApodAdapter.ApodHolder>() {

    protected var apodList = listOf<ApodEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ApodHolder(ItemApodBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ApodHolder, position: Int) {
        val data = apodList[position]
        holder.bindData(data)
    }

    fun swapData(apodList: List<ApodEntity>) {
        this.apodList = apodList
        notifyDataSetChanged()
    }

    override fun getItemCount() = apodList.size

    inner class ApodHolder(binding: ItemApodBinding) : BaseViewHolder<ItemApodBinding>(binding) {
        fun bindData(data: ApodEntity) {
            setState(data)
            setData(data)
            setListener(data)
        }

        private fun setData(data: ApodEntity) {
            binding.ivApod.load(data.url) {
                placeholder(ColorDrawable(Color.LTGRAY))
            }
        }

        private fun setListener(data: ApodEntity) {
            binding.ivFavorite.setOnClickListener {

            }
            binding.ivApod.setOnClickListener {

            }
        }

        private fun setState(data: ApodEntity) {
            binding.ivFavorite.colorFilter = data.favoriteColorFilter
        }

    }
}