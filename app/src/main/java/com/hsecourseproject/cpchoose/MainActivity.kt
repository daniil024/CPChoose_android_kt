package com.hsecourseproject.cpchoose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hsecourseproject.cpchoose.cplist.CPListFragment
import com.hsecourseproject.cpchoose.createcp.CreateCPFragment
import com.hsecourseproject.cpchoose.login.LoginFragment
import com.hsecourseproject.cpchoose.utils.UtilsSingleton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isUserLoggedIn()
    }

    private fun isUserLoggedIn() {
        UtilsSingleton.init(application)

        val sharedPreference =
            getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreference.getBoolean(getString(R.string.is_logged_in), false)
        if (isLoggedIn) {
            Toast.makeText(this, "User is already logged in", Toast.LENGTH_LONG)
                .show()
            startFragment(CPListFragment.newInstance())
            //startFragment(CreateCPFragment.newInstance())
        } else {
            startFragment(LoginFragment.newInstance())
            supportActionBar?.hide()
        }
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, fragment, fragment::class.java.simpleName)
            .commit()
    }
}