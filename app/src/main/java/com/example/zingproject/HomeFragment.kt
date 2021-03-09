package com.example.zingproject

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zingproject.database.User
import com.example.zingproject.database.UserDatabase
import com.example.zingproject.repository.UserRepository
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var userViewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userDao = context?.let { UserDatabase.getInstance(it).userDao }
        val repository = userDao?.let { UserRepository(it) }
        val factory = repository?.let { UserViewModelFactory(it) }
        userViewModel = ViewModelProvider(this, factory!!).get(UserViewModel::class.java)

        userViewModel.insert(User(0, "User1", "johnDoe@gmail.com"))
        userViewModel.insert(User(0, "User2", "alexa@gmail.com"))
        userViewModel.insert(User(0, "User3", "ana@gmail.com"))
        userViewModel.insert(User(0, "User4", "kate@gmail.com"))

        initRecyclerView()

    }

    private fun initRecyclerView() {
        recylcerViewHome.layoutManager = LinearLayoutManager(context)
        displayUsersList()
    }

    private fun displayUsersList() {
        userViewModel.users.observe(viewLifecycleOwner, Observer {
            Log.e("TAG", it.toString())
            recylcerViewHome.adapter = RecyclerViewAdapter(it,{selectedItem:User -> listItemClicked(selectedItem)})
        })
    }

    private fun listItemClicked(user: User){
        userViewModel.delete(user)
    }
}