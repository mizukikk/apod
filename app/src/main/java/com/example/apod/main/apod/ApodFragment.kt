package com.example.apod.main.apod

import android.os.Bundle
import android.view.View
import com.example.apod.R
import com.example.apod.base.BaseFragment
import com.example.apod.databinding.FragmentApodBinding
import com.example.apod.main.apod.adapter.ApodPageAdapter
import com.example.apod.main.apod.viewmodel.ApodListViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class ApodFragment : BaseFragment<FragmentApodBinding>(R.layout.fragment_apod) {

    private val apodPageAdapter by lazy { ApodPageAdapter(this) }

    override fun bindView(view: View) = FragmentApodBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.vpApod.adapter = apodPageAdapter
        TabLayoutMediator(binding.tabApod, binding.vpApod) { tab, pos ->
            tab.text = apodPageAdapter.getTabTitle(pos)
        }.attach()
    }

}