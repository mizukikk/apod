package com.example.apod.main.apod.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.example.apod.base.BaseAdapter
import com.example.apod.base.BaseViewHolder
import com.example.apod.databinding.ItemApodBinding
import com.example.apod.db.entity.ApodEntity
import com.example.apod.main.apod.data.ApodDetailArgs

class ApodAdapter : BaseAdapter<ApodAdapter.ApodHolder>() {

    private var listener: AdapterListener? = null
    private var apodList = listOf<ApodEntity>()
    val lastId
        get() = try {
            apodList.last()._id!!
        } catch (e: NoSuchElementException) {
            -1
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ApodHolder(ItemApodBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ApodHolder, position: Int) {
        val data = apodList[position]
        holder.bindData(data)
    }

    fun swapData(apodList: List<ApodEntity>) {
        val diffCall = ApodDiffCall(this.apodList, apodList)
        val result = DiffUtil.calculateDiff(diffCall)
        result.dispatchUpdatesTo(this)
        this.apodList = apodList
    }

    override fun getItemCount() = apodList.size

    fun setAdapterListener(listener: AdapterListener) {
        this.listener = listener
    }

    interface AdapterListener {
        fun openApodDetail(args: ApodDetailArgs)
        fun toggleFavorite(apodEntity: ApodEntity)
    }

    inner class ApodHolder(binding: ItemApodBinding) : BaseViewHolder<ItemApodBinding>(binding) {
        fun bindData(data: ApodEntity) {
            setState(data)
            setData(data)
            setListener(data)
        }

        private fun setData(data: ApodEntity) {
            binding.ivApod.load(data.url) {
                crossfade(true)
                placeholder(ColorDrawable(Color.LTGRAY))
            }
        }

        private fun setListener(data: ApodEntity) {
            binding.ivFavorite.setOnClickListener {
                if (itemClickable) {
                    data.favorite = data.favorite.not()
                    listener?.toggleFavorite(data)
                    notifyItemChanged(adapterPosition)
                }
            }
            binding.ivApod.setOnClickListener {
                if (itemClickable) {
                    listener?.openApodDetail(ApodDetailArgs(data))
                }
            }
        }

        private fun setState(data: ApodEntity) {
            binding.ivFavorite.colorFilter = data.favoriteColorFilter
        }

    }
}