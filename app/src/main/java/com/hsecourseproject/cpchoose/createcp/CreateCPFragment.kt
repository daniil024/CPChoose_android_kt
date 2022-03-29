package com.hsecourseproject.cpchoose.createcp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hsecourseproject.cpchoose.R

class CreateCPFragment : Fragment() {

    companion object {
        fun newInstance() = CreateCPFragment()
    }

    private lateinit var createCPViewModel: CreateCPViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_cp_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createCPViewModel = ViewModelProvider(this)[CreateCPViewModel::class.java]
        // TODO: Use the ViewModel
    }

}