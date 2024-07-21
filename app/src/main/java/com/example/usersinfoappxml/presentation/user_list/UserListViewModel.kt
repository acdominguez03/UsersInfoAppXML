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
        getUserList()
    }

    fun getUserList() {
        _uiState.tryEmit(
            _uiState.value.copy(
                userList = sharedPreferencesHelper.getUsers()
            )
        )
    }

    data class UserListState(
        val userList: List<UserModel> = emptyList(),
        val userListFiltered: List<UserModel> = emptyList()
    )
}