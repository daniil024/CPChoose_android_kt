package com.hsecourseproject.cpchoose.createcp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.databinding.CreateCpFragmentBinding

class CreateCPFragment : Fragment() {

    companion object {
        fun newInstance() = CreateCPFragment()
    }

    private lateinit var createCPViewModel: CreateCPViewModel

    private var _binding: CreateCpFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreateCpFragmentBinding.inflate(inflater, container, false)
        createCPViewModel = ViewModelProvider(this)[CreateCPViewModel::class.java]

        binding.createCPViewModel = createCPViewModel
        binding.lifecycleOwner = viewLifecycleOwner


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}