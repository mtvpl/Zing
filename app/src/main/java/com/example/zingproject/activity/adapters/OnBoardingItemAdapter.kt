package com.example.zingproject.activity.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zingproject.R
import com.example.zingproject.model.OnBoardingItem

class OnBoardingItemAdapter(private val onBoardingItems: List<OnBoardingItem>):
RecyclerView.Adapter<OnBoardingItemAdapter.onBoardingItemViewHolder>(){


    inner class onBoardingItemViewHolder(view:View):RecyclerView.ViewHolder(view){

        private val imageOnBoarding= view.findViewById<ImageView>(R.id.imageOnBoard)
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)

        fun bind(onBoardingItem: OnBoardingItem){
            imageOnBoarding.setImageResource(onBoardingItem.onBoardingImage)
            textTitle.text = onBoardingItem.title
            textDescription.text = onBoardingItem.description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): onBoardingItemViewHolder {
       return onBoardingItemViewHolder(LayoutInflater.from(parent.context).inflate(
           R.layout.onboarding_item_container,
           parent,
           false
       ))
    }

    override fun onBindViewHolder(holder: onBoardingItemViewHolder, position: Int) {
        holder.bind(onBoardingItems[position])
    }

    override fun getItemCount(): Int {
       return onBoardingItems.size
    }
}