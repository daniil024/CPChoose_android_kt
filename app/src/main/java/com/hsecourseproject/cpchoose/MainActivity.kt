package com.hsecourseproject.cpchoose

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hsecourseproject.cpchoose.cpinfo.CPInfo
import com.hsecourseproject.cpchoose.cplist.CPListFragment
import com.hsecourseproject.cpchoose.createcp.CreateCPFragment
import com.hsecourseproject.cpchoose.login.LoginFragment
import com.hsecourseproject.cpchoose.utils.UtilsSingleton

class MainActivity : AppCompatActivity() {

    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isUserLoggedIn()
        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setupWithNavController(navController)
        //Delete the line below. it was for test
        //navController.navigate(R.id.action_loginFragment_to_CPInfo)
    }
    private fun isUserLoggedIn() {
        UtilsSingleton.init(application)

        val sharedPreference =
            getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreference.getBoolean(getString(R.string.is_logged_in), false)

        if (isLoggedIn) {
            Toast.makeText(this, "User is already logged in", Toast.LENGTH_LONG)
                .show()

            navController.navigate(R.id.action_loginFragment_to_CPListFragment)
        } else {
            //startFragment(LoginFragment.newInstance())
            supportActionBar?.hide()
        }
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, fragment, fragment::class.java.simpleName)
            .commit()
    }
}