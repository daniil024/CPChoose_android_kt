package com.hsecourseproject.cpchoose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.hsecourseproject.cpchoose.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val fr = LoginFragment()
        val fragment: Fragment? =
        supportFragmentManager.findFragmentByTag(LoginFragment::class.java.simpleName)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, fr, LoginFragment::class.java.simpleName)
            .commit()

    }
}