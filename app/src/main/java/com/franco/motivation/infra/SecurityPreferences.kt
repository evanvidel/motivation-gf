package com.franco.motivation.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    private val security: SharedPreferences =
        context.getSharedPreferences("motivation", Context.MODE_PRIVATE)
    //função para salvar
    fun storeString(key: String, str: String) {
        security.edit().putString(key, str).apply()
    }

    fun getString(key: String) : String {
        return security.getString(key, "") ?: ""
    }
}