package com.example.apod.main

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apod.R
import com.example.apod.di.ApodApplication

class MainActivity : AppCompatActivity(), MainInteractivity {

    private val progressDialog by lazy {
        ProgressDialog(this).apply {
            setMessage(ApodApplication.getString(R.string.d_message_loading))
            setCancelable(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showProgress() {
        if (progressDialog.isShowing.not()) {
            progressDialog.show()
        }
    }

    override fun dismissProgress() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }
}