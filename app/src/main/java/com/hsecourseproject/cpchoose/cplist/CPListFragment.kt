package com.hsecourseproject.cpchoose.cplist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.hsecourseproject.cpchoose.MainActivity
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.cplist.recycler.CPAdapter
import com.hsecourseproject.cpchoose.cplist.recycler.CPItemDecoration
import com.hsecourseproject.cpchoose.cplist.recycler.OnRecyclerItemClicked
import com.hsecourseproject.cpchoose.databinding.CpListFragmentBinding
import com.hsecourseproject.cpchoose.models.CourseProjectDTO
import com.hsecourseproject.cpchoose.models.enums.UserType
import com.hsecourseproject.cpchoose.utils.UtilsSingleton

class CPListFragment : Fragment() {

    companion object {
        fun newInstance() = CPListFragment()
    }

    private lateinit var cpListViewModel: CPListViewModel

    private var _binding: CpListFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var recycler: RecyclerView? = null

    private var adapter: CPAdapter? = null

    private val navHostFragment by lazy {
        Navigation.findNavController(activity as MainActivity, R.id.fragmentContainerView)
    }

    private var bottomNavigationView: BottomNavigationView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CpListFragmentBinding.inflate(inflater, container, false)
        cpListViewModel = ViewModelProvider(this)[CPListViewModel::class.java]
        binding.cpListViewModel = cpListViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        bottomNavigationView = activity?.findViewById(R.id.bottomNavigation)
        activity?.findViewById<Toolbar>(R.id.defaultEmptyToolbar)?.visibility = View.VISIBLE


        bottomNavigationView?.visibility = View.VISIBLE

        if (UtilsSingleton.INSTANCE.getUserType() == UserType.STUDENT) {
            binding.proposedToUser.visibility = View.GONE
            //bottomNavigationView?.menu?.findItem(R.id.profileFragment)?.isVisible = false
            //bottomNavigationView?.menu?.findItem(R.id.CPApprovingFragment)?.isVisible = false
        }

        setupTBG()

        cpListViewModel.cpListData.observe(viewLifecycleOwner) { data ->
            if (data != null)
                updateData(data)
        }

        cpListViewModel.filterBtnClicked.observe(viewLifecycleOwner){wasClicked->
            if(wasClicked){
                findNavController().navigate(R.id.action_CPListFragment_to_filterDialog)
                cpListViewModel.disableFilterClick()
            }
        }

        val currentFragment = findNavController().getBackStackEntry(R.id.CPListFragment)
        val dialogObserver = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME
                && currentFragment.savedStateHandle.contains("filterData")) {
                val result = currentFragment.savedStateHandle.get<ArrayList<String>>("filterData")
                cpListViewModel.getFilteredAvailableCP(result?.get(0), result?.get(1))
            }
        }
        val dialogLifecycle = currentFragment.lifecycle
        dialogLifecycle.addObserver(dialogObserver)
        viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                dialogLifecycle.removeObserver(dialogObserver)
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupRecycler()
        binding.cpFullList.callOnClick()
    }

    private fun setupRecycler() {
        recycler = binding.cpListRecycler
        adapter = CPAdapter(clickListener)
        recycler?.adapter = adapter
        recycler?.layoutManager = LinearLayoutManager(requireContext())

        //recycler?.addItemDecoration(CPItemDecoration(R.dimen.cp_list_recycler_view_item_padding))

        recycler?.apply {
//            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            addItemDecoration(
                CPItemDecoration(
                    requireContext().resources.getDimension(R.dimen.cp_list_recycler_view_item_padding)
                        .toInt()
                )
            )
        }
    }

    private fun setupTBG() {
        cpListViewModel.cpFullList.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                checkButtons(R.id.cpFullList)
                colorButtons(binding.cpFullList)
            }
        }

        cpListViewModel.cpCreatedByUser.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                checkButtons(R.id.createdByUser)
                colorButtons(binding.createdByUser)
            }
        }

        cpListViewModel.cpProposedToProfessor.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                checkButtons(R.id.proposedToUser)
                colorButtons(binding.proposedToUser)
            }
        }
    }

    private fun checkButtons(check: Int) {
        val tbg = binding.listSwitcherButtonToggleGroup
        tbg.clearChecked()
        tbg.check(check)
    }

    private fun colorButtons(colored: MaterialButton) {
        val whiteColor = ContextCompat.getColorStateList(
            requireContext(), R.color.white
        )
        binding.proposedToUser.backgroundTintList = whiteColor
        binding.createdByUser.backgroundTintList = whiteColor
        binding.cpFullList.backgroundTintList = whiteColor

        colored.backgroundTintList = ContextCompat.getColorStateList(
            requireContext(), R.color.main_blue
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateData(cp: List<CourseProjectDTO>) {
//        val callback = CPCallback(cpListViewModel.cpListData.value!!, cp)
//        val diff = DiffUtil.calculateDiff(callback)
//        Log.i("my_tag:", recycler.toString())
//        Log.i("my_tag:", recycler?.adapter.toString())
//        diff.dispatchUpdatesTo(recycler?.adapter!!)
//        (recycler?.adapter as? CPAdapter)?.bindCourseProjects(cp)

        (recycler?.adapter as? CPAdapter)?.apply {
            bindCourseProjects(cp)
            adapter?.notifyDataSetChanged()
        }
    }

    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(cp: CourseProjectDTO) {
            val bundle: Bundle =
                bundleOf(CourseProjectDTO::class.java.simpleName to Gson().toJson(cp))
            navHostFragment.navigate(R.id.action_CPListFragment_to_CPInfo, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recycler = null
        adapter = null
    }
}