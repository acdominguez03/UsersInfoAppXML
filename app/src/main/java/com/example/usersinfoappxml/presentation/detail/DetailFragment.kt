package com.example.usersinfoappxml.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.usersinfoappxml.data.SharedPreferencesHelper
import com.example.usersinfoappxml.databinding.FragmentDetailBinding
import com.example.usersinfoappxml.databinding.FragmentUserListBinding
import com.example.usersinfoappxml.presentation.user_list.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel: DetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)

        viewModelResponse()

        return binding.root
    }

    private fun viewModelResponse() {
        lifecycleScope.launch {
            viewModel.uiState.collect { response ->
                response?.let { user ->
                    binding.tvName.text = user.name
                    binding.tvBirthdate.text = "Birthdate: ${user.birthDate}"
                    binding.tvFavoriteColor.text = "Favorite color: ${user.favoriteColor}"
                    binding.tvFavoriteNumber.text = "Favorite number: ${user.favoriteNumber}"
                }
            }
        }
    }

}