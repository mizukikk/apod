package com.example.apod.main.apod

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.apod.R
import com.example.apod.base.BaseFragment
import com.example.apod.component.layoutmanager.OverScrollGridLayoutManager
import com.example.apod.databinding.FragmentApodListBinding
import com.example.apod.main.apod.adapter.ApodAdapter
import com.example.apod.main.apod.data.ApodListArgs
import com.example.apod.main.apod.viewmodel.ApodListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ApodListFragment : BaseFragment<FragmentApodListBinding>(R.layout.fragment_apod_list) {

    companion object {
        private val TAG = ApodListFragment::class.java.simpleName
        fun newInstance(args: ApodListArgs) = ApodListFragment().apply {
            arguments = Bundle().apply {
                putParcelable(TAG, args)
            }
        }
    }

    private lateinit var args: ApodListArgs
    private val viewModel by viewModel<ApodListViewModel>()
    private val apodAdapter by lazy { ApodAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            args = it.getParcelable(TAG)!!
        }
    }

    override fun bindView(view: View) = FragmentApodListBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
        setListener()
        viewModel.getApodList()
    }

    private fun setListener() {
        binding.rvApod.layoutManager.let { lm ->
            if (lm is OverScrollGridLayoutManager)
                lm.setOverScrollListener { overScrollBottom ->


                }
        }
    }

    private fun initView() {
        binding.rvApod.adapter = apodAdapter
        binding.rvApod.layoutManager = OverScrollGridLayoutManager(requireContext(), 3)
        binding.rvApod.setHasFixedSize(true)
    }

    private fun initViewModel() {
        viewModel.progressEvent.observe(viewLifecycleOwner) { show ->
            if (show)
                parentActivity?.showProgress()
            else
                parentActivity?.dismissProgress()
        }
        viewModel.apodListLiveData.observe(viewLifecycleOwner) {
            apodAdapter.swapData(it)
        }
    }
}