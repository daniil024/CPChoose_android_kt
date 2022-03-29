package com.hsecourseproject.cpchoose.cplist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.cplist.models.CourseProjectDTO
import com.hsecourseproject.cpchoose.cplist.models.enums.CPMode
import com.hsecourseproject.cpchoose.cplist.models.enums.CPStatus
import com.hsecourseproject.cpchoose.cplist.models.enums.CPType
import com.hsecourseproject.cpchoose.cplist.network.CPNetwork
import com.hsecourseproject.cpchoose.cplist.recycler.CPAdapter
import com.hsecourseproject.cpchoose.cplist.recycler.CPItemDecoration
import com.hsecourseproject.cpchoose.cplist.recycler.OnCPCardClickListener
import com.hsecourseproject.cpchoose.databinding.CpListFragmentBinding
import com.hsecourseproject.cpchoose.login.models.enums.UserType
import com.hsecourseproject.cpchoose.utils.UtilsSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CPListFragment : Fragment(), OnCPCardClickListener {

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CpListFragmentBinding.inflate(inflater, container, false)

        cpListViewModel = ViewModelProvider(this)[CPListViewModel::class.java]

        activity?.application?.let { UtilsSingleton.init(it) }
        if (UtilsSingleton.INSTANCE.getUserType() == UserType.STUDENT) {
            binding.proposedToUser.visibility = View.GONE
            binding.bottomNavigation.menu.findItem(R.id.user_profile_menu_item).isVisible = false
        }

        recycler = binding.cpListRecycler

        cpListViewModel.getAllAvailableCP()

        adapter = CPAdapter()
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

        setupTBG()

        cpListViewModel.cpListData.observe(viewLifecycleOwner) { data ->
            if (data != null /*&& data.isNotEmpty()*/)
                updateData(data)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    private fun setupTBG() {
        binding.listSwitcherButtonToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.cpFullList -> {
                        cpListViewModel.getAllAvailableCP()
                        checkButtons(R.id.cpFullList)
                        colorButtons(binding.cpFullList)
                    }
                    R.id.createdByUser -> {
                        cpListViewModel.getCPCreatedByUser()
                        checkButtons(R.id.createdByUser)
                        colorButtons(binding.createdByUser)
                    }
                    R.id.proposedToUser -> {
                        checkButtons(R.id.proposedToUser)
                        colorButtons(binding.proposedToUser)
                    }
                }
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

    private fun updateData(cp: List<CourseProjectDTO>) {
        (recycler?.adapter as? CPAdapter)?.apply {
            bindCourseProjects(cp)
            adapter?.notifyDataSetChanged()
        }
    }

    override fun onCPCardClicked(courseProjectDTO: CourseProjectDTO) {
        // TODO: call new intent to open card detailed info
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recycler = null
        adapter = null
    }

}