package com.example.apod.main.apod

import android.os.Bundle
import android.view.View
import com.example.apod.R
import com.example.apod.base.BaseFragment
import com.example.apod.databinding.FragmentApodListBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            args = it.getParcelable(TAG)!!
        }
    }

    override fun bindView(view: View) = FragmentApodListBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        viewModel.getApodList()
    }

    private fun initViewModel() {
        viewModel.progressEvent.observe(viewLifecycleOwner) { show ->
            if (show)
                parentActivity?.showProgress()
            else
                parentActivity?.dismissProgress()
        }
        viewModel.apodListLiveData.observe(viewLifecycleOwner) {

        }
    }
}