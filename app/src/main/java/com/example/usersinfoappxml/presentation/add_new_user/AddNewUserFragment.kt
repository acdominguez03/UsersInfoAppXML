package com.example.usersinfoappxml.presentation.add_new_user

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.usersinfoappxml.databinding.FragmentAddNewUserBinding
import com.example.usersinfoappxml.extensions.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class AddNewUserFragment : Fragment() {
    private lateinit var binding: FragmentAddNewUserBinding

    private val viewModel: AddNewUserViewModel by viewModels()

    private val initialDate = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewUserBinding.inflate(layoutInflater)

        binding.btnAddUser.setOnClickListener {
            checkValues()
        }

        binding.btnBirthday.setOnClickListener {
            showDialog(this.requireContext())
        }

        binding.root.setOnClickListener {
            it.hideKeyboard()
        }

        return binding.root
    }

    private fun checkValues() {
        if (binding.etName.text.isEmpty()) {
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = "Introduce un nombre por favor"
        } else if (binding.etCity.text.isEmpty()) {
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = "Introduce tu ciudad favorita por favor"
        } else if (binding.etFavoriteNumber.text.isEmpty()) {
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = "Introduce tu número favorito por favor"
        } else if (binding.btnBirthday.text.isEmpty()) {
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = "Introduce tu fecha de nacimiento por favor"
        } else if (binding.etFavoriteColor.text.isEmpty()) {
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = "Introduce tu color favorito por favor"
        } else {
            viewModel.addNewUser(
                name = binding.etName.text.toString(),
                favoriteCity = binding.etCity.text.toString(),
                favoriteNumber = binding.etFavoriteNumber.text.toString(),
                birthdate = binding.btnBirthday.text.toString(),
                favoriteColor = binding.etFavoriteColor.text.toString()
            ) {
                findNavController().popBackStack()
            }
        }
    }

    private fun showDialog(context: Context) {
        val datePickerDialog = DatePickerDialog(
            context, { view, year, month, dayOfMonth ->
                view.maxDate = Date().time
                val date = Calendar.getInstance()
                date.set(Calendar.YEAR, year)
                date.set(Calendar.MONTH, month)
                date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                val formattedDate = simpleDateFormat.format(date.time)
                binding.btnBirthday.text = formattedDate
            }, initialDate.get(Calendar.YEAR), initialDate.get(Calendar.MONTH), initialDate.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

}

