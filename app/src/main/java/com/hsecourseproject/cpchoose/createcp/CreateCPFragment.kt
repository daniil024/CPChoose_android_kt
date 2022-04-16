package com.hsecourseproject.cpchoose.createcp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.hsecourseproject.cpchoose.MainActivity
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

    private val navHostFragment by lazy {
        Navigation.findNavController(activity as MainActivity, R.id.fragmentContainerView)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreateCpFragmentBinding.inflate(inflater, container, false)
        createCPViewModel = ViewModelProvider(this)[CreateCPViewModel::class.java]

        binding.createCPViewModel = createCPViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupUX()
        createCPViewModel.fillProfessorData()
        return binding.root
    }

    private fun setupUX() {
        createCPViewModel.navigateToList.observe(viewLifecycleOwner) { wasSent ->
            if (wasSent) {
                navHostFragment.navigate(R.id.action_createCPFragment_to_CPListFragment)
            }
        }
    }

}