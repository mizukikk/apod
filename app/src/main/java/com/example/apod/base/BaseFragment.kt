package com.example.apod.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.apod.main.MainInteractivity


abstract class BaseFragment<B : ViewBinding>(@LayoutRes private val layoutId: Int) :
    Fragment(layoutId) {

    private var _binding: B? = null
    protected val binding get() = _binding!!
    protected var parentActivity: MainInteractivity? = null
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(requireContext()).inflate(layoutId, container, false)
        _binding = bindView(view)
        return view
    }

    abstract fun bindView(view: View): B

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainInteractivity)
            parentActivity = context
    }

    override fun onDetach() {
        super.onDetach()
        parentActivity = null
    }
}