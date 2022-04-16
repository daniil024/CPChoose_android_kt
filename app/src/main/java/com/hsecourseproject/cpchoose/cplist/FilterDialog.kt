package com.hsecourseproject.cpchoose.cplist

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.hsecourseproject.cpchoose.MainActivity
import com.hsecourseproject.cpchoose.R

class FilterDialog : AppCompatDialogFragment() {

    private var professorSurnameEdiText: EditText? = null

    private var wordEdiText: EditText? = null

    private var listener: FilterDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        val view = inflater?.inflate(R.layout.filter_dialog, null)
        builder.setView(view).setTitle("Фильтр")
            .setNegativeButton("Отмена") { _, _ -> }
            .setPositiveButton("Применить") { _, _ ->
                val professorSurname = professorSurnameEdiText?.text.toString()
                val word = wordEdiText?.text.toString()
                val result = arrayListOf(professorSurname, word)
                //listener?.applyText(professorSurname, word)
                //sendData(professorSurname, word)
                findNavController().previousBackStackEntry?.savedStateHandle?.set(
                    "filterData",
                    result
                )
            }

        professorSurnameEdiText = view?.findViewById(R.id.sortByProfessorSurnameText)
        wordEdiText = view?.findViewById(R.id.sortByCPWordText)
        return builder.create()
    }

    private fun sendData(professorSurname: String, word: String) {
        val result = arrayListOf(professorSurname, word)
        requireActivity().supportFragmentManager.setFragmentResult(
            "filterData",
            bundleOf("filter" to result)
        )
        //setFragmentResult("filterData", bundleOf("filter" to result))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

//        try {
//            listener = context as FilterDialogListener
//        } catch (e: ClassCastException) {
//            throw ClassCastException("$context must implement FilterDialogListener")
//        }

    }

    interface FilterDialogListener {
        fun applyText(professorSurname: String, word: String)
    }
}