package com.example.usersinfoappxml.presentation.add_new_user

import androidx.lifecycle.ViewModel
import com.example.usersinfoappxml.data.SharedPreferencesHelper
import com.example.usersinfoappxml.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNewUserViewModel @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper
): ViewModel() {

    fun addNewUser(
        name: String,
        favoriteCity: String,
        favoriteNumber: String,
        birthdate: String,
        favoriteColor: String,
        completion: () -> Unit
    ) {
        val newUser = UserModel(
            id = sharedPreferencesHelper.getUserId(),
            name = name,
            favoriteCity = favoriteCity,
            favoriteNumber = favoriteNumber,
            birthDate = birthdate,
            favoriteColor = favoriteColor
        )

        val newList = sharedPreferencesHelper.getUsers()
        newList.add(newUser)
        sharedPreferencesHelper.saveUsers(newList)

        sharedPreferencesHelper.setUserId()
        completion()
    }

}