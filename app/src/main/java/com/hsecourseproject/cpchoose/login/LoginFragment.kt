package com.hsecourseproject.cpchoose.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.databinding.FragmentLoginBinding


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
        binding.lifecycleOwner = viewLifecycleOwner

        setupUX()

        return binding.root
        //return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private fun setupUX() {
        loginViewModel.errorToastEmail.observe(viewLifecycleOwner) { hasError ->
            if (hasError) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.input_email_null_error_message),
                    Toast.LENGTH_SHORT
                )
                    .show()
                loginViewModel.doneToastEmail()
                binding.emailTextInput.error = getString(R.string.input_email_null_error_message)
            }
        }

        loginViewModel.errorToastEmailDomain.observe(viewLifecycleOwner) { hasError ->
            if (hasError) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.input_email_not_right_domain_error_message),
                    Toast.LENGTH_SHORT
                )
                    .show()
                loginViewModel.doneToastEmailDomain()
                binding.emailTextInput.error =
                    getString(R.string.input_email_not_right_domain_error_message)
            }
        }

        loginViewModel.errorToastCode.observe(viewLifecycleOwner) { hasError ->
            if (hasError) {
                Toast.makeText(requireContext(), "Code is incorrect", Toast.LENGTH_LONG).show()
                loginViewModel.doneToastCode()
                binding.codeTextInput.error = getString(R.string.input_code_error_message)
            }
        }

        loginViewModel.navigateToCodeChecker.observe(viewLifecycleOwner) { hasError ->
            if (hasError) {
                Toast.makeText(requireContext(), "Good enough", Toast.LENGTH_LONG)
                    .show()
                binding.codeTextInput.visibility = View.VISIBLE
                binding.loginButton.visibility = View.VISIBLE
                binding.emailTextInput.isErrorEnabled = false

                loginViewModel.doneToastFinish()
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

        loginViewModel.navigateToApp.observe(viewLifecycleOwner) { isCodeRight ->
            if (isCodeRight) {
                Navigation.findNavController(view = requireView()).navigate(R.id.action_loginFragment_to_CPListFragment)
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