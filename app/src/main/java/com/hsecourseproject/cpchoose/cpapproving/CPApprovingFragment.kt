package com.hsecourseproject.cpchoose.cpapproving

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.cpapproving.recycler.ApprovedCPAdapter
import com.hsecourseproject.cpchoose.cpapproving.recycler.ApprovedCPItemDecoration
import com.hsecourseproject.cpchoose.databinding.CpApprovingFragmentBinding
import com.hsecourseproject.cpchoose.models.ApprovedCPDTO

class CPApprovingFragment : Fragment() {

    companion object {
        fun newInstance() = CPApprovingFragment()
    }

    private lateinit var cpApprovingViewModel: CPApprovingViewModel

    private var _binding: CpApprovingFragmentBinding? = null

    private val binding get() = _binding!!

    private var recycler: RecyclerView? = null
    private var adapter: ApprovedCPAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CpApprovingFragmentBinding.inflate(inflater, container, false)
        cpApprovingViewModel = ViewModelProvider(this)[CPApprovingViewModel::class.java]
        binding.cpApprovingViewModel = cpApprovingViewModel
        binding.lifecycleOwner = viewLifecycleOwner


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupRecycler()
        setupObserver()
        cpApprovingViewModel.getWaitingForApproving()
    }

    private fun setupObserver() {
        cpApprovingViewModel.cpApprovingFullListData.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                updateData(data)
            }
        }
    }

    private fun setupRecycler() {
        recycler = binding.CPApprovingRecycler
        adapter = ApprovedCPAdapter(cpApprovingViewModel)

        recycler?.adapter = adapter
        recycler?.layoutManager = LinearLayoutManager(requireContext())

        recycler?.apply {
            addItemDecoration(
                ApprovedCPItemDecoration(
                    requireContext().resources.getDimension(R.dimen.cp_list_recycler_view_item_padding)
                        .toInt()
                )
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateData(approvedCP: List<ApprovedCPDTO>) {
//        val callback = CPCallback(cpListViewModel.cpListData.value!!, cp)
//        val diff = DiffUtil.calculateDiff(callback)
//        Log.i("my_tag:", recycler.toString())
//        Log.i("my_tag:", recycler?.adapter.toString())
//        diff.dispatchUpdatesTo(recycler?.adapter!!)
//        (recycler?.adapter as? CPAdapter)?.bindCourseProjects(cp)

        (recycler?.adapter as? ApprovedCPAdapter)?.apply {
            bindApprovedCourseProjects(approvedCP)
            adapter?.notifyDataSetChanged()
        }
    }
}