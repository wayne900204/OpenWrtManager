package com.example.openwrtmanager.ui.identity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.openwrtmanager.R
import com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.database.IdentityItem


class IdentityAdapter : RecyclerView.Adapter<IdentityAdapter.MyViewHolder>() {

    private val TAG = IdentityAdapter::class.qualifiedName
    var feeds: List<IdentityItem> = listOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IdentityAdapter.MyViewHolder {
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

        fun bindView(item: IdentityItem) {
            display.text = item.displayName

            itemIdentity.setOnClickListener {
                val action = IdentityFragmentDirections.actionIdentityFragmentToAddIdentityFragment(
                    item.uuid,
                    item.displayName,
                    item.username,
                    item.password, item.id, true
                )
                itemView.findNavController().navigate(action)
            }
        }
    }

    fun updateAdapterData(todos: List<IdentityItem>) {
        feeds = todos
        notifyDataSetChanged()
    }

}
