package com.example.usersinfoappxml.presentation.user_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usersinfoappxml.data.SharedPreferencesHelper
import com.example.usersinfoappxml.databinding.FragmentUserListBinding
import com.example.usersinfoappxml.model.UserModel
import com.example.usersinfoappxml.presentation.user_list.adapter.UserListAdapter
import kotlinx.coroutines.launch

class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding

    private lateinit var usersAdapter: UserListAdapter

    private lateinit var viewModel: UserListViewModel

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(layoutInflater)
        sharedPreferencesHelper = SharedPreferencesHelper(this.requireContext())
        viewModel = UserListViewModel(sharedPreferencesHelper = sharedPreferencesHelper)

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                usersAdapter = UserListAdapter(
                    userList = state.userList,
                    onClickListener = { id ->
                        //findNavController().navigate()
                    },
                    onDeleteClickListener = { position -> }
                )
            }
        }

        binding.rvUserList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = usersAdapter
        }

        return binding.root
    }
}