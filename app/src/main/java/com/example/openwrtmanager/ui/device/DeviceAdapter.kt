package com.example.openwrtmanager.com.example.openwrtmanager.ui.device

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.database.DeviceItem
import com.example.openwrtmanager.databinding.ListItemIdentitiyBinding
import com.example.openwrtmanager.ui.device.DeviceFragmentDirections


class DeviceAdapter : RecyclerView.Adapter<DeviceAdapter.MyViewHolder>() {
    private val TAG = DeviceAdapter::class.qualifiedName
    var feeds: List<DeviceItem> = listOf()
    var row_index = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceAdapter.MyViewHolder {
        return MyViewHolder(ListItemIdentitiyBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(feeds[position],position)
    }

    override fun getItemCount() = feeds.size

    inner class MyViewHolder(private val binding:ListItemIdentitiyBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: DeviceItem, position: Int) {
            binding.display.text = item.displayName

            binding.itemIdentity.setOnClickListener {


                if(row_index==position){
                                    val action =
                    DeviceFragmentDirections.actionDeviceFragmentToAddDeviceFragment(item.id, true)
                itemView.findNavController().navigate(action)
                }
                row_index=position;
                notifyDataSetChanged()


            }
            if(row_index==position){
                binding.isUsing.visibility = View.VISIBLE
            }else{
                binding.isUsing.visibility = View.GONE
            }
        }

    }

    fun updateAdapterData(todos: List<DeviceItem>) {
        feeds = todos
        notifyDataSetChanged()
    }
}
