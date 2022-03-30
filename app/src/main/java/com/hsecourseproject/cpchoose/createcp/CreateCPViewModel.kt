package com.hsecourseproject.cpchoose.createcp

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel

class CreateCPViewModel(application: Application) : AndroidViewModel(application), Observable {


    fun sendCP() {
        Toast.makeText(getApplication(), "test", Toast.LENGTH_LONG).show()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}