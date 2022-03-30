package com.hsecourseproject.cpchoose.cplist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.models.CourseProjectDTO
import com.hsecourseproject.cpchoose.cplist.recycler.CPAdapter
import com.hsecourseproject.cpchoose.cplist.recycler.CPItemDecoration
import com.hsecourseproject.cpchoose.cplist.recycler.OnCPCardClickListener
import com.hsecourseproject.cpchoose.databinding.CpListFragmentBinding
import com.hsecourseproject.cpchoose.models.enums.UserType
import com.hsecourseproject.cpchoose.utils.UtilsSingleton

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
        binding.cpListViewModel = cpListViewModel
        binding.lifecycleOwner = this

        if (UtilsSingleton.INSTANCE.getUserType() == UserType.STUDENT) {
            binding.proposedToUser.visibility = View.GONE
            binding.bottomNavigation.menu.findItem(R.id.user_profile_menu_item).isVisible = false
        }

        setupTBG()

        cpListViewModel.cpListData.observe(viewLifecycleOwner) { data ->
            if (data != null)
                updateData(data)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupRecycler()
        binding.cpFullList.callOnClick()
    }

    private fun setupRecycler() {
        recycler = binding.cpListRecycler
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