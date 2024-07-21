package com.example.usersinfoappxml.data

import android.content.Context
import android.content.SharedPreferences
import com.example.usersinfoappxml.model.UserModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesHelper(private val context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("UsersPreferences", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    private val gson = Gson()

    fun saveUsers(userList: List<UserModel>, key: String) {
        val json = gson.toJson(userList)
        editor.putString(key, json).apply()
    }

    fun getUsers(key: String): List<UserModel> {
        val json = sharedPreferences.getString(key, null)
        val type = object : TypeToken<List<UserModel>>() {}.type
        return gson.fromJson(json, type) ?: listOf()
    }
}