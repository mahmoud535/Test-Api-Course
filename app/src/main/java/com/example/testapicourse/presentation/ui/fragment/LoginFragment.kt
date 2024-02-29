package com.example.testapicourse.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.testapicourse.R
import com.example.testapicourse.databinding.FragmentLoginBinding
import com.example.testapicourse.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.regex.Pattern

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val userViewModel: ListUserViewModel by viewModels()

    val userName = "Ahmed"
    val Password = "123M@m"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        actions()

        return binding.root
    }

    private fun actions() {
        binding.idLogin.setOnClickListener {
            val username = binding.IdUserName.text.toString()
            val password = binding.IdPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty() || username.equals(userName) && password.equals(Password) ){
                if (isValidPassword(password)) {
                    val actions =
                        LoginFragmentDirections.actionLoginFragmentToListFragment(username)
                    view?.findNavController()?.navigate(actions)
                }else {
                    Toast.makeText(requireContext(), "Invalid password format", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isValidPassword(password:String):Boolean{
        // Password should contain at least 6 characters, 1 uppercase letter, and 1 special character
         val passwordRegex = "^(?=.*[A-Z])(?=.*[@#\$%*]).{6,}\$"
          val pattern = Pattern.compile(passwordRegex)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }
}

