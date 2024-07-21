package com.example.usersinfoappxml.presentation.user_list

import androidx.lifecycle.ViewModel
import com.example.usersinfoappxml.common.Constants
import com.example.usersinfoappxml.data.SharedPreferencesHelper
import com.example.usersinfoappxml.model.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserListViewModel(
    val sharedPreferencesHelper: SharedPreferencesHelper
): ViewModel() {

    private val _uiState: MutableStateFlow<UserListState> by lazy { MutableStateFlow(UserListState()) }
    val uiState: StateFlow<UserListState> get() = _uiState

    init {
        addUserList()
    }

    fun addUserList() {
        val users = sharedPreferencesHelper.getUsers(key = Constants.USERSKEY)

        if (users.isEmpty()) {
            sharedPreferencesHelper.saveUsers(
                userList = listOf(
                    UserModel(
                        id = 0,
                        name = "Andres",
                        favoriteCity = "Sidney",
                        favoriteNumber = "12",
                        birthDate = "10/08/2003"
                    )
                ),
                key = Constants.USERSKEY
            )
        }

        getUserList()
    }

    fun getUserList() {
        _uiState.tryEmit(
            _uiState.value.copy(
                userList = sharedPreferencesHelper.getUsers(key = Constants.USERSKEY)
            )
        )
    }

    data class UserListState(
        val userList: List<UserModel> = emptyList(),
        val userListFiltered: List<UserModel> = emptyList()
    )
}