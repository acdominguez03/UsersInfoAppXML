package com.example.usersinfoappxml.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usersinfoappxml.data.SharedPreferencesHelper
import com.example.usersinfoappxml.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val state: SavedStateHandle
): ViewModel() {

    private val _uiState: MutableStateFlow<UserModel?> by lazy { MutableStateFlow(null) }
    val uiState: StateFlow<UserModel?> get() = _uiState

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            state.get<Int>("userId")?.let { id ->
                val users = sharedPreferencesHelper.getUsers()
                _uiState.value = users.first {
                    it.id == id
                }
            }
        }
    }
}