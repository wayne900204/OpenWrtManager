package com.example.openwrtmanager.com.example.openwrtmanager.ui.information

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.openwrtmanager.R
import com.example.openwrtmanager.com.example.openwrtmanager.ui.information.model.InfoResponseModelItem

class InfoAdapter : ListAdapter<InfoResponseModelItem, RecyclerView.ViewHolder>(
    PlantDiffCallback()
) {
    override fun getItemViewType(position: Int): Int {
        return getItem(position).id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> TodoViewHolder(parent)
            else -> TodoViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val todo = getItem(position).id) {
            1 -> (holder as TodoViewHolder).bind(getItem(position))
            else -> (holder as TodoViewHolder).bind(getItem(position))
        }
    }

    override fun submitList(list: MutableList<InfoResponseModelItem>?) {
        super.submitList(list)
    }
}

class TodoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_system, parent, false)
) {

    private val checkbox: TextView = itemView.findViewById(R.id.status_view)

    fun bind(todo: InfoResponseModelItem) {
        checkbox.text = todo.result.toString()
        Log.d("SUSSS", "bind: "+"123")
    }

}

private class PlantDiffCallback : DiffUtil.ItemCallback<InfoResponseModelItem>() {

    override fun areItemsTheSame(oldItem: InfoResponseModelItem, newItem: InfoResponseModelItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: InfoResponseModelItem, newItem: InfoResponseModelItem): Boolean {
        return oldItem == newItem
    }
}