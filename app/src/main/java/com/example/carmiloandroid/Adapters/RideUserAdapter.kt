package com.example.carmiloandroid.Adapters

import android.databinding.DataBindingUtil

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.carmiloandroid.Models.Ride
import com.example.carmiloandroid.Models.User


import com.example.carmiloandroid.R
import com.example.carmiloandroid.databinding.ItemRideBinding
import com.example.carmiloandroid.databinding.ItemRideUserBinding

class RideUserAdapter(private val ridesUserList : ArrayList<Ride>) : RecyclerView.Adapter<RideUserAdapter.CustomViewHolder>()  {

    //Na implementeren van
    // dataBinding {
    //enabled = true
    //}
    inner class CustomViewHolder (val binding: ItemRideUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ride) {
            binding.ride = item
            binding.executePendingBindings()
        }
    }

    //when the viewHolder is created it adds the item to the holder (this happens for all items)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding : ItemRideUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_ride_user, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(ridesUserList[position])
    }

    //Gets the items inside the list
    override fun getItemCount(): Int {
        return ridesUserList.size
    }

    fun setRides(result: List<Ride>) {
        this.ridesUserList.clear()
        this.ridesUserList.addAll(result)
        notifyDataSetChanged()
    }

}