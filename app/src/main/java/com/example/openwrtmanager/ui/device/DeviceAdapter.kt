package com.example.openwrtmanager.com.example.openwrtmanager.ui.device

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.openwrtmanager.R
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.database.DeviceItem
import com.example.openwrtmanager.ui.device.AddDeviceFragment
import com.example.openwrtmanager.ui.device.DeviceFragmentDirections


class DeviceAdapter : RecyclerView.Adapter<DeviceAdapter.MyViewHolder>() {
    private val TAG = DeviceAdapter::class.qualifiedName
    var feeds: List<DeviceItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceAdapter.MyViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_identitiy, parent, false)
            .run(::MyViewHolder)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(feeds[position])
    }

    override fun getItemCount() = feeds.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val display = itemView.findViewById<TextView>(R.id.display)
        private val itemIdentity = itemView.findViewById<ConstraintLayout>(R.id.item_identity)

        fun bindView(item: DeviceItem) {
            display.text = item.displayName
            display.text = item.displayName

            itemIdentity.setOnClickListener {
                val action =
                    DeviceFragmentDirections.actionDeviceFragmentToAddDeviceFragment(item.id, true)
                itemView.findNavController().navigate(action)
            }
        }
    }

    fun updateAdapterData(todos: List<DeviceItem>) {
        feeds = todos
        notifyDataSetChanged()
    }
}
