package com.hsecourseproject.cpchoose.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.login.models.enums.UserType

class UtilsSingleton private constructor(private val application: Application) {

    companion object {
        lateinit var INSTANCE: UtilsSingleton
            // Instance setter is only accessible from within the class.
            private set

        lateinit var sharedPreference: SharedPreferences

        // Custom init function is called from outside and replaces
        // THE WHOLE SINGLETON with a new instance
        // to avoid internal dependencies on the old context.
        fun init(application: Application) {
            INSTANCE = UtilsSingleton(application)
            sharedPreference = application.getSharedPreferences(
                application.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
            )
        }
    }

    fun getUserType(): UserType? {
        val sharedPreference =
            application.getSharedPreferences(
                application.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
            )
        return sharedPreference.getString(application.getString(R.string.user_type), "")
            ?.let { UserType.valueOf(it) }
    }

    fun writeUserId(id: Int) {
        val editor = sharedPreference.edit()
        val userId = application.resources.getString(R.string.user_id_from_db)
        editor.putInt(userId, id)
        editor.apply()
    }
}