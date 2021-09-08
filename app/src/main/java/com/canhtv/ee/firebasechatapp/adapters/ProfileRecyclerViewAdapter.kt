package com.canhtv.ee.firebasechatapp.adapters

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.canhtv.ee.firebasechatapp.databinding.ProfileItemViewBinding

class ProfileRecyclerViewAdapter(
    private val listAction: List<String>,
    private val icon: Drawable
) : RecyclerView.Adapter<ProfileRecyclerViewAdapter.ViewHolder>() {

        class ViewHolder(val binding: ProfileItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

            init {
                itemView.setOnClickListener {
                }
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val binding = ProfileItemViewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            with(viewHolder.binding) {
                profileItemViewIcon.setImageDrawable(icon)
                profileItemViewText.text = listAction[position]
            }
        }

        override fun getItemCount() = listAction.size
}