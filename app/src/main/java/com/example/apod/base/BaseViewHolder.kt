package com.example.apod.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BaseViewHolder<B : ViewBinding>(protected val binding: B) :
    RecyclerView.ViewHolder(binding.root)