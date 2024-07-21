package com.example.usersinfoappxml.presentation.user_list

import androidx.lifecycle.ViewModel
import com.example.usersinfoappxml.common.Constants
import com.example.usersinfoappxml.data.SharedPreferencesHelper
import com.example.usersinfoappxml.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper
): ViewModel() {

    private val _uiState: MutableStateFlow<UserListState> by lazy { MutableStateFlow(UserListState()) }
    val uiState: StateFlow<UserListState> get() = _uiState

    init {
        getUserList()
    }

    fun getUserList() {
        val list = sharedPreferencesHelper.getUsers()
        _uiState.tryEmit(
            _uiState.value.copy(
                userList = sharedPreferencesHelper.getUsers()
            )
        )
    }

    fun removeUser(id: Int) {
        val list = sharedPreferencesHelper.getUsers()
        list.removeAt(id)
        sharedPreferencesHelper.saveUsers(list)
        _uiState.tryEmit(
            _uiState.value.copy(
                userList = list
            )
        )
    }

    data class UserListState(
        val userList: List<UserModel> = emptyList(),
        val userListFiltered: List<UserModel> = emptyList()
    )
}