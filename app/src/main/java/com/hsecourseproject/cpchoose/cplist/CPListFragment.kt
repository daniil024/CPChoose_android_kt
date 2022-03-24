package com.hsecourseproject.cpchoose.cplist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.cplist.models.CourseProjectDTO
import com.hsecourseproject.cpchoose.cplist.models.enums.CPMode
import com.hsecourseproject.cpchoose.cplist.models.enums.CPStatus
import com.hsecourseproject.cpchoose.cplist.models.enums.CPType
import com.hsecourseproject.cpchoose.cplist.recycler.CPAdapter
import com.hsecourseproject.cpchoose.databinding.CpListFragmentBinding
import com.hsecourseproject.cpchoose.databinding.FragmentLoginBinding

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CpListFragmentBinding.inflate(inflater, container, false)

        cpListViewModel = ViewModelProvider(this)[CPListViewModel::class.java]

        recycler = binding.cpListRecycler
        val cp = listOf(
            CourseProjectDTO(
                id = null,
                userId = null,
                titleRus = "Android-app for course project topics distribution\n",
                titleEng = null,
                type = CPType.PROGRAM,
                mode = CPMode.COMMAND,
                membersCount = null,
                projectInitiator = null,
                companySubdivision = null,
                mentorFullName = "Сосновский Г. М.",
                annotation = "Android-приложение будет служить для поиска, выбора и добавления курсовых\n" +
                        "проектов преподавателями и студентами, а также для последующего генерирования\n" +
                        "необходимой документации, предоставляемой в качестве сопутствующих документов. Приложение...",
                projectGoal = null,
                projectTasks = null,
                participantsTasks = null,
                projectResults = null,
                additionalInfo = null,
                workPlace = null,
                studentsRequirements = null,
                contacts = null,
                startDate = null,
                finishDate = null,
                selectionForm = null,
                evaluationCriteria = null,
                status = CPStatus.APPROVED
            )
        )

        recycler?.adapter = CPAdapter()
        //updateData(cp)

        (recycler?.adapter as? CPAdapter)?.apply {
            bindCourseProjects(cp)
        }

        return inflater.inflate(R.layout.cp_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    private fun updateData(cp: List<CourseProjectDTO>) {
        (recycler?.adapter as? CPAdapter)?.apply {
            bindCourseProjects(cp)
        }
    }
}