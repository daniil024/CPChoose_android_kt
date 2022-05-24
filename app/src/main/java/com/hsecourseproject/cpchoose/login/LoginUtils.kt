package com.hsecourseproject.cpchoose.login

import android.util.Patterns
import java.util.regex.Pattern

class LoginUtils {

    companion object {
        private val EMAIL_ADDRESS: Pattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "(edu\\.)?" +
                    "hse\\.ru")

        fun isEmailCorrect(email: String): Boolean {
            // Patterns.EMAIL_ADDRESS.mather(email).matches()

            val pattern:Pattern = EMAIL_ADDRESS
            return pattern.matcher(email).matches()
        }
    }


}