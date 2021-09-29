package com.canhtv.ee.firebasechatapp.adapters

import android.content.Context
import android.util.Log
import android.view.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.models.UserProfile
import com.canhtv.ee.firebasechatapp.databinding.ContactItemViewBinding

class  ContactRecyclerViewAdapter(
    private val data: ArrayList<UserProfile>,
    private val context: Context,
    val onItemClickHandler: (UserProfile) -> Unit,
) : RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ContactItemViewBinding, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClicked(absoluteAdapterPosition)
            }

            with(binding.contactToolbar) {
                overflowIcon = AppCompatResources.getDrawable(this.context, R.drawable.ic_overflow_action)
                setOnMenuItemClickListener { menu ->
                    when (menu.itemId) {
                        R.id.ic_voice_call_contact -> {
                            onItemClicked(absoluteAdapterPosition)
                            return@setOnMenuItemClickListener true
                        }
                        else -> {
                            onItemClicked(absoluteAdapterPosition)
                            return@setOnMenuItemClickListener true
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContactItemViewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding) {
            onItemClickHandler(data[it])
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding.contactToolbar.title = data[position].username
        if (data[position].avatarUrl.equals("default")) {
            viewHolder.binding.contactAvatarImageView.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_person))
        }
        if (data[position].isOnline.equals("online")) {
            viewHolder.binding.contactIsOnlineImageView.visibility = View.VISIBLE
            Log.d("ContactAdapter", data[position].isOnline.toString())
        }

        viewHolder.binding.contactIsOnlineImageView.visibility = View.VISIBLE
    }

    override fun getItemCount() = data.size

}
