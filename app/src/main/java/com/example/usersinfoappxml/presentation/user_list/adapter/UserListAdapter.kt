package com.example.usersinfoappxml.presentation.user_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.usersinfoappxml.R
import com.example.usersinfoappxml.model.UserModel

class UserListAdapter(
    private var userList: List<UserModel>,
    private val onClickListener: (Int) -> Unit
): RecyclerView.Adapter<UserListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserListViewHolder(layoutInflater.inflate(R.layout.user_row, parent, false))
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.render(
            position = position,
            user = userList[position],
            onClickListener = onClickListener
        )
    }
}