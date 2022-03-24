package com.hsecourseproject.cpchoose.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButtonToggleGroup
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.databinding.FragmentLoginBinding
import com.hsecourseproject.cpchoose.login.models.enums.UserType


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var loginViewModel: LoginFragmentViewModel

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        loginViewModel = ViewModelProvider(this)[LoginFragmentViewModel::class.java]

        binding.loginFragmentViewModel = loginViewModel
        binding.lifecycleOwner = this

        setupUX()

        return binding.root
        //return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Use the ViewModel

    }

    private fun setupUX() {
        loginViewModel.errorToastEmail.observe(viewLifecycleOwner) { hasError ->
            if (hasError) {
                Toast.makeText(requireContext(), "Please check your e-mail", Toast.LENGTH_SHORT)
                    .show()
                loginViewModel.doneToastEmail()
            }
        }

        loginViewModel.errorToastEmailDomain.observe(viewLifecycleOwner) { hasError ->
            if (hasError) {
                Toast.makeText(
                    requireContext(),
                    "E-mail must be in hse.ru domain", Toast.LENGTH_SHORT
                )
                    .show()
                loginViewModel.doneToastEmailDomain()
            }
        }

        loginViewModel.errorToastCode.observe(viewLifecycleOwner) { hasError ->
            if (hasError) {
                Toast.makeText(requireContext(), "Code is incorrect", Toast.LENGTH_LONG)
                    .show()
                loginViewModel.doneToastCode()
            }
        }

        loginViewModel.navigateToFinish.observe(viewLifecycleOwner) { hasError ->
            if (hasError) {
                Toast.makeText(requireContext(), "Good enough", Toast.LENGTH_LONG)
                    .show()
                binding.codeTextInput.visibility = View.VISIBLE
                binding.loginButton.visibility = View.VISIBLE


                loginViewModel.doneToastFinish()
            }
        }

        loginViewModel.errorToastCode.observe(viewLifecycleOwner) { hasError ->
            if (hasError) {
                Toast.makeText(requireContext(), "Code is incorrect", Toast.LENGTH_LONG).show()
                loginViewModel.doneToastCode()
            }
        }

        loginViewModel.professorButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                binding.toggleButtonStudent.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(), R.color.white
                )
                binding.toggleButtonProfessor.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(), R.color.main_blue
                )
            }
        }

        loginViewModel.studentButtonClicked.observe(viewLifecycleOwner) { isClicked ->
            if (isClicked) {
                binding.toggleButtonProfessor.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(), R.color.white
                )
                binding.toggleButtonStudent.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(), R.color.main_blue
                )
            }
        }

        val tbg = binding.loginToggleButtonGroup
        tbg.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.toggleButtonStudent -> {
                        tbg.uncheck(R.id.toggleButtonProfessor)
                        tbg.check(R.id.toggleButtonStudent)
                    }
                    R.id.toggleButtonProfessor -> {
                        tbg.uncheck(R.id.toggleButtonStudent)
                        tbg.check(R.id.toggleButtonProfessor)
                    }
                }
            }
        }
    }


}