package com.example.openwrtmanager.com.example.openwrtmanager.ui.information

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.openwrtmanager.com.example.openwrtmanager.ui.information.adapter.SystemInfoHolder
import com.example.openwrtmanager.com.example.openwrtmanager.ui.information.model.InfoResponseModelItem
import com.example.openwrtmanager.databinding.ListItemSystemBinding

class InfoAdapter : ListAdapter<InfoResponseModelItem, RecyclerView.ViewHolder>(
    PlantDiffCallback()
) {
    override fun getItemViewType(position: Int): Int {
        return getItem(position).id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
//            1 -> SystemInfoHolder(parent)
            1 -> SystemInfoHolder(ListItemSystemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            else -> SystemInfoHolder(ListItemSystemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItem(position).id) {
            1 -> (holder as SystemInfoHolder).bind(getItem(position), getItem(1))
//            else -> (holder as TodoViewHolder).bind(getItem(position))
        }
    }

    override fun submitList(list: MutableList<InfoResponseModelItem>?) {
        super.submitList(list)
    }

    override fun getItemCount(): Int {
        return super.getItemCount() - 1
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<InfoResponseModelItem>() {

    override fun areItemsTheSame(
        oldItem: InfoResponseModelItem,
        newItem: InfoResponseModelItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: InfoResponseModelItem,
        newItem: InfoResponseModelItem
    ): Boolean {
        return oldItem == newItem
    }
}