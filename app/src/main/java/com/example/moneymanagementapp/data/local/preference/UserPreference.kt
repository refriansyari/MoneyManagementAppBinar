package com.example.moneymanagementapp.data.local.preference;

import android.content.Context
import android.content.SharedPreferences

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class UserPreference(context: Context) {
    private var preference: SharedPreferences = context.getSharedPreferences(NAME, MODE)

    companion object {
        private const val NAME = "UserPreference" //app name or else
        private const val MODE = Context.MODE_PRIVATE
        private val PREF_APP_ALREADY_OPEN_FIRST_TIME =
            Pair("PREF_APP_ALREADY_OPEN_FIRST_TIME", false)
    }

    var alreadyOpenFirstTime: Boolean
        get() = preference.getBoolean(
            PREF_APP_ALREADY_OPEN_FIRST_TIME.first,
            PREF_APP_ALREADY_OPEN_FIRST_TIME.second
        )
        set(value) = preference.edit {
            it.putBoolean(PREF_APP_ALREADY_OPEN_FIRST_TIME.first, value)
        }

}

private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = edit()
    operation(editor)
    editor.apply()
}

private fun SharedPreferences.delete() {
    edit().clear().apply()
}