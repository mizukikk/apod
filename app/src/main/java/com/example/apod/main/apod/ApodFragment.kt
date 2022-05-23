package com.example.apod.main.apod

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.apod.R
import com.example.apod.base.BaseFragment
import com.example.apod.databinding.FragmentApodBinding
import com.example.apod.main.apod.viewmodel.ApodViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ApodFragment : BaseFragment<FragmentApodBinding>(R.layout.fragment_apod) {

    val viewModel: ApodViewModel by viewModel()

    override fun bindView(view: View) = FragmentApodBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}