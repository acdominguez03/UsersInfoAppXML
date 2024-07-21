package com.example.usersinfoappxml.presentation.user_list.adapter

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.usersinfoappxml.databinding.UserRowBinding
import com.example.usersinfoappxml.model.UserModel

class UserListViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = UserRowBinding.bind(view)

    fun render(position: Int, user: UserModel, onClickListener: (Int) -> Unit, onDeleteClickListener: (Int) -> Unit) {
        binding.tvUserName.text = user.name
        binding.tvFavoriteCity.text = "Favorite city: ${user.favoriteCity}"
        binding.ivColor.setBackgroundColor(Color.BLACK)

        itemView.setOnClickListener {
            onClickListener(
                user.id
            )
        }
    }

}