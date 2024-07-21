package com.example.usersinfoappxml.data

import android.content.Context
import android.content.SharedPreferences
import com.example.usersinfoappxml.common.Constants
import com.example.usersinfoappxml.model.UserModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesHelper(private val context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("UsersPreferences", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    private val gson = Gson()

    fun saveUsers(userList: ArrayList<UserModel>) {
        val json = gson.toJson(userList)
        editor.putString(Constants.USERS_KEY, json).apply()
    }

    fun getUsers(): ArrayList<UserModel> {
        val json = sharedPreferences.getString(Constants.USERS_KEY, null)
        val type = object : TypeToken<ArrayList<UserModel>>() {}.type
        return gson.fromJson(json, type) ?: arrayListOf()
    }

    fun setUserId() {
        val id = getUserId()
        if (id == -1) {
            editor.putInt(Constants.USER_ID_KEY, 0).apply()
        } else {
            editor.putInt(Constants.USER_ID_KEY, id + 1).apply()
        }
    }

    fun getUserId(): Int {
        return sharedPreferences.getInt(Constants.USER_ID_KEY, -1)
    }
}