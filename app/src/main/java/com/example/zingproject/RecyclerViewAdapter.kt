package com.example.zingproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.zingproject.database.User
import com.example.zingproject.databinding.ListItemLayoutBinding

class RecyclerViewAdapter(
    private val usersList: List<User>,
    private val clickListener: (User) -> Unit
) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemLayoutBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item_layout, parent, false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList[position], clickListener)
    }
}


class MyViewHolder(val binding: ListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User, clickListener: (User) -> Unit) {
        binding.textName.text = user.title
        binding.textEmail.text = user.body
        binding.textId.text = user.id.toString()
        binding.textMsgRead.text = user.msgRead.toString()
        binding.textDeleted.text  = user.deleted.toString()
        binding.textMsgType.text  = user.msgType
        binding.textSenderId.text = user.senderId
        binding.textSenderRef.text = user.senderRef
        binding.textSeqNo.text = user.seqNo.toString()
        binding.textDataPart.text = user.dataPart
        binding.textTimeStamp.text = user.date


        binding.imageDelete.setOnClickListener {
            clickListener(user)
        }

        binding.parentLayout.setOnClickListener {
            binding.expandableLayout.visibility = View.VISIBLE
        }


    }


}