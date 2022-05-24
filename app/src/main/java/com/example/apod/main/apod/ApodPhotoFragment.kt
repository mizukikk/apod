package com.example.apod.main.apod

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import coil.load
import com.example.apod.R
import com.example.apod.base.BaseFragment
import com.example.apod.databinding.FragmentApodPhotoBinding
import com.example.apod.extension.setProgress
import com.example.apod.main.apod.data.ApodPhotoArgs

class ApodPhotoFragment : BaseFragment<FragmentApodPhotoBinding>(R.layout.fragment_apod_photo) {
    companion object {
        private val TAG = ApodPhotoFragment::class.java.simpleName
        fun naviTo(navController: NavController, args: ApodPhotoArgs) {
            navController.navigate(
                R.id.apodPhotoFragment,
                Bundle().apply {
                    putParcelable(TAG, args)
                })
        }
    }

    private lateinit var args: ApodPhotoArgs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            args = it.getParcelable(TAG)!!
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        showSystemBar()
    }

    private fun hideSystemBar() {
        activity?.window?.let { window ->
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowInsetsControllerCompat(window, window.decorView).let { controller ->
                controller.hide(WindowInsetsCompat.Type.navigationBars())
                controller.hide(WindowInsetsCompat.Type.statusBars())
                controller.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }

    private fun showSystemBar() {
        activity?.window?.let { window ->
            WindowCompat.setDecorFitsSystemWindows(window, true)
            WindowInsetsControllerCompat(window, window.decorView).let { controller ->
                controller.show(WindowInsetsCompat.Type.navigationBars())
                controller.show(WindowInsetsCompat.Type.statusBars())
                controller.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }

    override fun bindView(view: View) = FragmentApodPhotoBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivApod.load(args.photoUrl) {
            setProgress(binding.ivApod.context)
            crossfade(true)
        }
    }
}