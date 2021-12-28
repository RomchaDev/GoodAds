package org.romeo.layer_data.data_sources.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import org.romeo.layer_data.dto.TokenUser

class TokenUserDataSourceLocalImpl(
    private val preferences: SharedPreferences
) : TokenUserDataSourceLocal {
    override fun save(data: TokenUser) {
        val dataStr = Gson().toJson(data)

        preferences.edit().putString(KEY, dataStr).apply()
    }

    override fun get(): TokenUser? {
        val dataStr = preferences.getString(KEY, null)

        return dataStr?.let {
            Gson().fromJson(it, TokenUser::class.java)
        }
    }

    companion object {
        private const val KEY = "LOGIN_RESPONSE_KEY"
    }
}