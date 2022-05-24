package com.hsecourseproject.cpchoose.utils

import android.content.Context
import android.content.SharedPreferences
import com.hsecourseproject.cpchoose.R
import com.hsecourseproject.cpchoose.models.enums.CPMode
import com.hsecourseproject.cpchoose.models.enums.CPType
import com.hsecourseproject.cpchoose.models.enums.UserType

class UtilsSingleton private constructor(private val application: Context) {

    companion object {
        lateinit var INSTANCE: UtilsSingleton
            // Instance setter is only accessible from within the class.
            private set

        lateinit var sharedPreference: SharedPreferences

        // Custom init function is called from outside and replaces
        // THE WHOLE SINGLETON with a new instance
        // to avoid internal dependencies on the old context.
        fun init(application: Context) {
            INSTANCE = UtilsSingleton(application)
            sharedPreference = application.getSharedPreferences(
                application.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
            )
        }
    }


    fun setUserId(id: Int) {
        val editor = sharedPreference.edit()
        val userId = application.resources.getString(R.string.user_id_from_db)
        editor.putInt(userId, id)
        editor.apply()
    }

    fun getUserId(): Int {
        return sharedPreference.getInt(application.getString(R.string.user_id_from_db), 0)
    }

    fun setUserStudentId(studentId: Int) {
        val editor = sharedPreference.edit()
        val studentIdVar = application.resources.getString(R.string.user_student_id)
        editor.putInt(studentIdVar, studentId)
        editor.apply()
    }

    fun getUserStudentId(): Int {
        return sharedPreference.getInt(application.getString(R.string.user_student_id), 0)
    }

    fun setUserProfessorId(professorId: Int) {
        val editor = sharedPreference.edit()
        val professorIdVar = application.resources.getString(R.string.user_professor_id)
        editor.putInt(professorIdVar, professorId)
        editor.apply()
    }

    fun getUserProfessorId(): Int {
        return sharedPreference.getInt(application.getString(R.string.user_professor_id), 0)
    }

    fun setUserEmail(email: String) {
        val editor = sharedPreference.edit()
        val userEmailVar = application.resources.getString(R.string.user_email)
        editor.putString(userEmailVar, email)
        editor.apply()
    }

    fun getUserEmail(): String? {
        return sharedPreference.getString(application.getString(R.string.user_email), "")
    }

    fun setUserType(userType: UserType) {
        val editor = sharedPreference.edit()
        val userTypeVar = application.resources.getString(R.string.user_type)
        editor.putString(userTypeVar, userType.name)
        editor.apply()
    }

    fun getUserType(): UserType? {
        return sharedPreference.getString(application.getString(R.string.user_type), "")
            ?.let { UserType.valueOf(it) }
    }

    fun getCPType(id: Int): CPType {
        val type =
            application.resources.getStringArray(R.array.create_cp_project_types_spinner_items)[id]
        return if (type == CPType.PROGRAM.type) CPType.PROGRAM else CPType.RESEARCH
    }

    fun getCPMode(id: Int): CPMode {
        val mode =
            application.resources.getStringArray(R.array.create_cp_project_mode_spinner_items)[id]
        return if (mode == CPMode.INDIVIDUAL.mode) CPMode.INDIVIDUAL else CPMode.COMMAND
    }

}