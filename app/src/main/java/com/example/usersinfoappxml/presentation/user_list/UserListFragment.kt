package com.example.usersinfoappxml.presentation.user_list

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usersinfoappxml.R
import com.example.usersinfoappxml.data.SharedPreferencesHelper
import com.example.usersinfoappxml.databinding.FragmentUserListBinding
import com.example.usersinfoappxml.model.UserModel
import com.example.usersinfoappxml.presentation.user_list.adapter.UserListAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding

    private lateinit var usersAdapter: UserListAdapter

    private val viewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(layoutInflater)

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                usersAdapter = UserListAdapter(
                    userList = state.userList,
                    onClickListener = { id ->
                        findNavController().navigate(UserListFragmentDirections.actionUserListFragmentToDetailFragment(userId = id))
                    }
                )

                swipeToDelete()

                binding.tvMessage.isGone = state.userList.isNotEmpty()
            }
        }

        binding.rvUserList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = usersAdapter
        }

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(UserListFragmentDirections.actionUserListFragmentToAddNewUserFragment())
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUserList()
    }

    private fun swipeToDelete() {
        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedUser = viewModel.uiState.value.userList[viewHolder.adapterPosition]
                viewModel.uiState.value.userList

                if (direction == ItemTouchHelper.LEFT) {
                    viewModel.removeUser(viewHolder.adapterPosition)
                    usersAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                }

                Snackbar.make(binding.rvUserList, "Deleted " + deletedUser.name, Snackbar.LENGTH_LONG).show()
            }

        }).attachToRecyclerView(binding.rvUserList)
    }
}