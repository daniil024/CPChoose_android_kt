package com.hsecourseproject.cpchoose.login

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.databinding.FragmentLoginBinding
import com.hsecourseproject.cpchoose.login.models.UserResponse
import com.hsecourseproject.cpchoose.login.network.LoginNetwork
import kotlin.math.log


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

        setupView()

        return binding.root
        //return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Use the ViewModel

    }

    private fun setupView() {
        loginViewModel.errorToastEmail.observe(this, Observer { hasError ->
            if (hasError) {
                Toast.makeText(requireContext(), "Please check your e-mail", Toast.LENGTH_SHORT)
                    .show()
                loginViewModel.doneToastEmail()
            }
        })

        loginViewModel.errorToastEmailDomain.observe(this, Observer { hasError ->
            if (hasError) {
                Toast.makeText(
                    requireContext(),
                    "E-mail must be in hse.ru domain", Toast.LENGTH_SHORT
                )
                    .show()
                loginViewModel.doneToastEmailDomain()
            }
        })

        loginViewModel.errorToastCode.observe(this, Observer { hasError ->
            if (hasError) {
                Toast.makeText(requireContext(), "Code is incorrect", Toast.LENGTH_LONG)
                    .show()
                loginViewModel.doneToastCode()
            }
        })

        loginViewModel.navigateToFinish.observe(this, Observer { hasError ->
            if (hasError) {
                Toast.makeText(requireContext(), "Good enough", Toast.LENGTH_LONG)
                    .show()
                binding.codeTextInput.visibility = View.VISIBLE
                binding.loginButton.visibility = View.VISIBLE


                loginViewModel.doneToastFinish()
            }
        })
    }
}