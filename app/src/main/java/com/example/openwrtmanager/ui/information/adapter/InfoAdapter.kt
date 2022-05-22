package com.example.openwrtmanager.com.example.openwrtmanager.ui.information

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.openwrtmanager.com.example.openwrtmanager.ui.information.adapter.IpNetworkStatusHolder
import com.example.openwrtmanager.com.example.openwrtmanager.ui.information.adapter.IpNetworkTrafficHolder
import com.example.openwrtmanager.com.example.openwrtmanager.ui.information.adapter.SystemInfoHolder
import com.example.openwrtmanager.com.example.openwrtmanager.ui.information.model.InfoResponseModelItem
import com.example.openwrtmanager.databinding.ListItemIpStatusBinding
import com.example.openwrtmanager.databinding.ListItemNetworkTrafficBinding
import com.example.openwrtmanager.databinding.ListItemSystemBinding


// 1. onBindViewHolder 查看第幾  SAMPLE:2 個 item 要對應哪一個畫面
// 2. 再去查看 getItemViewType 的那個 item 2-> 對應哪一個參數 SAMPLE:2->INT3
// 3. 再去看 onCreateViewHolder 裡面的 viewType 是對應哪一個 viewholder SAMPLE 3-> NetworkTraffic
class InfoAdapter : ListAdapter<InfoResponseModelItem, RecyclerView.ViewHolder>(
    PlantDiffCallback()
) {
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("DEBUG", "onCreateViewHolder: "+viewType)
        return when (viewType) {
            0 -> SystemInfoHolder(ListItemSystemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            1 -> IpNetworkStatusHolder(ListItemIpStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            2 -> IpNetworkTrafficHolder(ListItemNetworkTrafficBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            else -> SystemInfoHolder(ListItemSystemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            0 -> (holder as SystemInfoHolder).bind(currentList[0] as InfoResponseModelItem,currentList[1] as InfoResponseModelItem)
            2 -> {
                (holder as IpNetworkTrafficHolder).bind(currentList[3] as InfoResponseModelItem,currentList[4] as InfoResponseModelItem)
            }
            1 -> { (holder as IpNetworkStatusHolder).bind(currentList[4] as InfoResponseModelItem)}
            else->{}
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
        return oldItem.result == newItem.result
    }

    override fun areContentsTheSame(
        oldItem: InfoResponseModelItem,
        newItem: InfoResponseModelItem
    ): Boolean {
        return oldItem == newItem
    }
}