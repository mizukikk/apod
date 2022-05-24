package com.example.apod.main.apod

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import coil.load
import com.example.apod.R
import com.example.apod.base.BaseFragment
import com.example.apod.databinding.FragmentApodDetailBinding
import com.example.apod.main.apod.data.ApodDetailArgs

class ApodDetailFragment : BaseFragment<FragmentApodDetailBinding>(R.layout.fragment_apod_detail) {

    companion object {
        private val TAG = ApodDetailFragment::class.java.simpleName
        fun naviTo(navController: NavController, args: ApodDetailArgs) {
            navController.navigate(
                R.id.apodDetailFragment,
                Bundle().apply {
                    putParcelable(TAG, args)
                })
        }
    }

    private lateinit var args: ApodDetailArgs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            args = it.getParcelable(TAG)!!
        }
    }

    override fun bindView(view: View) = FragmentApodDetailBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.ivApod.load(args.apodEntity.hdUrl) {
            placeholder(ColorDrawable(Color.LTGRAY))
            crossfade(true)
        }
        binding.tvTitle.text = args.apodEntity.title
        binding.tvDescription.text = args.apodEntity.description
        binding.tvCopyright.text = args.apodEntity.copyright
        binding.tvDate.text = args.apodEntity.date
    }
}