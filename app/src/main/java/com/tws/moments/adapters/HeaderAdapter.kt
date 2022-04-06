package com.tws.moments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tws.moments.api.entry.UserBean
import com.tws.moments.databinding.ItemMomentHeadBinding
import com.tws.moments.viewholders.HeaderViewHolder

class HeaderAdapter : RecyclerView.Adapter<HeaderViewHolder>() {

    var userBean: UserBean? = null
        set(value) {
            field = value
            notifyItemChanged(0)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        return HeaderViewHolder(
            ItemMomentHeadBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(userBean)
    }

    override fun getItemCount(): Int {
        return 1
    }
}