package com.example.testapicourse.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.testapicourse.R
import com.example.testapicourse.databinding.FragmentListBinding
import com.example.testapicourse.domain.model.User
import com.example.testapicourse.presentation.adapter.NoteRecyclerView
import com.example.testapicourse.presentation.adapter.OnListItemClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment(), OnListItemClick {

    lateinit var binding: FragmentListBinding
    private val recyclerview: NoteRecyclerView by lazy { NoteRecyclerView() }
    private var userName: String? = null

    private val userViewModel: ListUserViewModel by viewModels()


    private val args: ListFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)

        userName = args.userName

        actions()

        return binding.root
    }

    private fun actions() {
        observeApiUserList()
        observeAddApiUserList()
        setUpRecyclerview()
        listener()
        getApiUsers()
    }

    private fun observeApiUserList() {
        lifecycleScope.launchWhenStarted {
            userViewModel.userApiStateFlow.collect { user ->
                recyclerview.setList(user)
                binding.progressBar.visibility = View.GONE
            }
            getApiUsers()
        }
    }

    private fun observeAddApiUserList() {
        lifecycleScope.launchWhenStarted {
            userViewModel.adduserApiStateFlow.collect { user ->
                if (user != null) {
                    Toast.makeText(
                        requireContext(),
                        "the user ${user.name} is added successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(requireContext(), "connection failed!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun listener() {
        binding.btnAdd.setOnClickListener {
            val body = binding.txtMessage.text.toString()
            addApiUser(body)
            binding.progressBar.visibility = View.VISIBLE
            getApiUsers()
        }
    }

    private fun addApiUser(body:String){
        userViewModel.addUserApi(
            User(
                0,
                userName!!,
                body,
                R.drawable.ic_launcher_background
            )
        )
    }

    private fun getApiUsers() {
        userViewModel.getUserApi()
        binding.progressBar.visibility = View.VISIBLE
    }


    private fun setUpRecyclerview() {
        binding.ivShowData.adapter = recyclerview
        recyclerview.onListItemClick = this
    }

    override fun onItemClick(user: User) {
        userViewModel.deleteApiUser(user.id)
        Toast.makeText(
            requireContext(),
            "the User ${user.name} is deleted Successfully",
            Toast.LENGTH_SHORT
        ).show()
        getApiUsers()
    }


}