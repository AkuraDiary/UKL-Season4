package com.smktelkommlg.cores.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smktelkommlg.cores.databinding.ListItemLayoutBinding

class ItemAdapter(
    private val item: ArrayList<Item>,
    private val clickListener: (String, View) -> Unit
) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: List<Item>?) {
        item.apply {
            clear()
            items?.let { addAll(it) }
        }
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val binding: ListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item, click: (String, View) -> Unit) {
            binding.apply {
                data = item
                root.transitionName = item.name
                root.setOnClickListener { item.name?.let { it1 -> click(it1, root) } }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            ListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(item[position], clickListener)

    override fun getItemCount(): Int = item.size
}