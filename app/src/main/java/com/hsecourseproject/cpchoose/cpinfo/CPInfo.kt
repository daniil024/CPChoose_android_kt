package com.hsecourseproject.cpchoose.cpinfo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.hsecourseproject.cpchoose.MainActivity
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.databinding.CpInfoFragmentBinding
import com.hsecourseproject.cpchoose.models.CourseProjectDTO
import com.hsecourseproject.cpchoose.models.enums.UserType
import com.hsecourseproject.cpchoose.utils.UtilsSingleton


class CPInfo : Fragment() {

    companion object {
        fun newInstance() = CPInfo()
    }

    private var _binding: CpInfoFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var cpInfoViewModel: CPInfoViewModel

    private val navHostFragment by lazy {
        Navigation.findNavController(activity as MainActivity, R.id.fragmentContainerView)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CpInfoFragmentBinding.inflate(inflater, container, false)
        cpInfoViewModel = ViewModelProvider(this)[CPInfoViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.cpInfoViewModel = cpInfoViewModel

//        val actionbar = (activity as AppCompatActivity).supportActionBar
//        actionbar?.setDisplayHomeAsUpEnabled(true)

        navHostFragment.getBackStackEntry(R.id.CPListFragment)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val cp = arguments?.getString(CourseProjectDTO::class.java.simpleName)
        cpInfoViewModel.initFields(Gson().fromJson(cp, CourseProjectDTO::class.java))
        setupUX()
    }

    private fun setupUX() {
        val bottomNavigationView =
            activity?.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView?.visibility = View.GONE

        if (UtilsSingleton.INSTANCE.getUserId() == cpInfoViewModel.userId.value
            || UtilsSingleton.INSTANCE.getUserType() == UserType.PROFESSOR
        ) {
            binding.applyCPRequest.visibility = View.GONE
        }
    }
}