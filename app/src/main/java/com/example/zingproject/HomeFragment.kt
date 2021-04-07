package com.example.zingproject

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zingproject.Service.FirebaseService
import com.example.zingproject.database.User
import com.example.zingproject.database.UserDatabase
import com.example.zingproject.repository.UserRepository
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.properties.Delegates


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var userViewModel: UserViewModel
   var isDeleted by Delegates.notNull<Boolean>()
    var isRead by Delegates.notNull<Boolean>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userDao = context?.let { UserDatabase.getInstance(it).userDao }
        val repository = userDao?.let { UserRepository(it) }
        val factory = repository?.let { UserViewModelFactory(it) }
        userViewModel = ViewModelProvider(this, factory!!).get(UserViewModel::class.java)

        val title = arguments?.getString("title")
        val body = arguments?.getString("body")
        val seqNo = arguments?.getString("seqNo")
        val senderRef = arguments?.getString("senderRef")
        val senderId = arguments?.getString("senderId")
        val msgRef = arguments?.getString("msgRef")
        val msgType = arguments?.getString("msgType")
        val ts = arguments?.getString("ts")
        val deleted = arguments?.getString("deleted")
        val msgRead = arguments?.getString("msgRead")
        val dataPart=  arguments?.getString("dataPart")
        isDeleted = deleted != "false"
        isRead = msgRead != "false"


        if (title != null && body != null) {

                userViewModel.insert(User(0, "name", "email",seqNo!!.toInt(),title,senderRef!!,senderId!!,body,msgRef!!,msgType!!,dataPart!!,ts!!,isDeleted,isRead))
        }

        /*
        var clipBoardManager =  context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
         var clipdata = ClipData.newPlainText("FCM Token",textView.text)
        clipBoardManager?.setPrimaryClip(clipdata)

         */
        textView.setText(String.format(getString(R.string.fcm_token, FirebaseService.token)))
        //   userViewModel.insert(User(0, "User1", "johnDoe@gmail.com"))


        initRecyclerView()


    }

    private fun initRecyclerView() {
        recylcerViewHome.layoutManager = LinearLayoutManager(context)
        displayUsersList()
    }

    private fun displayUsersList() {
        userViewModel.users.observe(viewLifecycleOwner, Observer {
            Log.e("TAG", it.toString())
            recylcerViewHome.adapter =
                RecyclerViewAdapter(it, { selectedItem: User -> listItemClicked(selectedItem) })
        })
    }

    private fun listItemClicked(user: User) {
        userViewModel.delete(user)
    }
}