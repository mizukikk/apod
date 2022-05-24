package com.example.apod.main.apod

import android.os.Bundle
import android.view.View
import com.example.apod.R
import com.example.apod.base.BaseFragment
import com.example.apod.databinding.FragmentApodBinding
import com.example.apod.main.MainShareViewModel
import com.example.apod.main.apod.adapter.ApodPageAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ApodFragment : BaseFragment<FragmentApodBinding>(R.layout.fragment_apod) {

    private val shareViewModel by sharedViewModel<MainShareViewModel>()
    private lateinit var apodPageAdapter: ApodPageAdapter

    override fun bindView(view: View) = FragmentApodBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initViewModel() {
        shareViewModel.apodCountLiveData.observe(viewLifecycleOwner) { apodCountMap ->
            apodCountMap.forEach {
                val count = it.value
                when (it.key) {
                    ApodPageAdapter.Type.All ->
                        binding.tabApod.getTabAt(0)?.text = apodPageAdapter.getTabTitle(0) + count
                    ApodPageAdapter.Type.Favorite ->
                        binding.tabApod.getTabAt(1)?.text = apodPageAdapter.getTabTitle(1) + count
                }
            }
        }
    }

    private fun initView() {
        apodPageAdapter = ApodPageAdapter(this)
        binding.vpApod.adapter = apodPageAdapter
        TabLayoutMediator(binding.tabApod, binding.vpApod) { tab, pos ->
            tab.text = apodPageAdapter.getTabTitle(pos)
        }.attach()
    }

}