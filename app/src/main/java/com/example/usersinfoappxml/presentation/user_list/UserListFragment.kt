package com.example.usersinfoappxml.presentation.user_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usersinfoappxml.R
import com.example.usersinfoappxml.databinding.FragmentUserListBinding
import com.example.usersinfoappxml.model.UserModel
import com.example.usersinfoappxml.presentation.user_list.adapter.UserListAdapter

class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding

    private lateinit var usersAdapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(layoutInflater)

        usersAdapter = UserListAdapter(
            userList = listOf(
                UserModel(
                    id = 0,
                    name = "Andres",
                    favoriteCity = "Sidney",
                    favoriteNumber = "12",
                    birthDate = "10/08/2003"
                )
            ),
            onClickListener = { id ->
                //findNavController().navigate()
            },
            onDeleteClickListener = { position -> }
        )

        binding.rvUserList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = usersAdapter
        }

        return binding.root
    }
}