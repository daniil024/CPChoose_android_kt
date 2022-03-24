package com.hsecourseproject.cpchoose.cplist

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.databinding.Observable

class CPListViewModel (application: Application) : AndroidViewModel(application), Observable {


    fun onFullListClicked(v: View){

    }

    fun onCreatedListClicked(v: View){

    }

    fun onProposedListClicked(v: View){

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}