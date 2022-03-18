//package com.hsecourseproject.cpchoose.login
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.EditText
//import android.widget.Toast
//import androidx.activity.viewModels
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProviders
//import com.google.android.material.button.MaterialButtonToggleGroup
//import com.google.android.material.textfield.TextInputLayout
//import com.hsecourseproject.cpchoose.R
//
//class LoginActivity : AppCompatActivity() {
//
//    private lateinit var loginViewModel: LoginFragmentViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_login)
//
//        supportActionBar?.hide()
//
//        val emailEditText = findViewById<EditText>(R.id.emailEditText)
//        emailEditText?.onFocusChangeListener = View.OnFocusChangeListener { _, _ ->
//            val rrr = findViewById<TextInputLayout>(R.id.emailTextInput)
//            if (rrr.hint == "E-mail")
//                rrr.hint = getString(R.string.input_email_hint)
//            else
//                rrr.hint = "E-mail"
//        }
//
//        val codeEditText = findViewById<EditText>(R.id.codeEditText)
//        codeEditText?.onFocusChangeListener = View.OnFocusChangeListener { _, _ ->
//            val rrr = findViewById<TextInputLayout>(R.id.codeTextInput)
//            if (rrr.hint == "Code")
//                rrr.hint = getString(R.string.input_code_hint)
//            else
//                rrr.hint = "Code"
//        }
//
//
//        val tbg = findViewById<MaterialButtonToggleGroup>(R.id.loginToggleButtonGroup)
//        tbg.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->
//
//            if (isChecked) {
//                when (checkedId) {
//                    R.id.toggleButtonStudent -> {
//                        tbg.check(R.id.toggleButtonStudent)
//                        tbg.uncheck(R.id.toggleButtonProfessor)
//                        Toast.makeText(this, "Student", Toast.LENGTH_SHORT)
//                            .show()
//                        Log.i("CheckedLOG: ", checkedId.toString())
//                    }
//                    R.id.toggleButtonProfessor -> {
//                        tbg.check(R.id.toggleButtonProfessor)
//                        tbg.uncheck(R.id.toggleButtonStudent)
//
//                        Toast.makeText(
//                            this,
//                            "Professor",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        Log.i("CheckedLOG: ", checkedId.toString())
//                    }
//                }
//            }
//        }
//
//    }
//}