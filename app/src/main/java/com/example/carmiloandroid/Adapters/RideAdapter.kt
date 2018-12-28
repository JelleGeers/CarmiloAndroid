package com.example.carmiloandroid.Adapters

import android.databinding.DataBindingUtil

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.carmiloandroid.Models.Ride
import com.example.carmiloandroid.Models.User


import com.example.carmiloandroid.R
import com.example.carmiloandroid.databinding.ItemRideBinding

class RideAdapter(private val ridesList : ArrayList<User>) : RecyclerView.Adapter<RideAdapter.CustomViewHolder>()  {

    //Na implementeren van
    // dataBinding {
    //enabled = true
    //}
    inner class CustomViewHolder (val binding: ItemRideBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            binding.user = item
            binding.executePendingBindings()
        }
    }

    //when the viewHolder is created it adds the item to the holder (this happens for all items)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding : ItemRideBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_ride, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(ridesList[position])
    }

    //Gets the items inside the list
    override fun getItemCount(): Int {
        return ridesList.size
    }

    fun setRides(result: List<User>) {
        this.ridesList.clear()
        this.ridesList.addAll(result)
        notifyDataSetChanged()
    }

}