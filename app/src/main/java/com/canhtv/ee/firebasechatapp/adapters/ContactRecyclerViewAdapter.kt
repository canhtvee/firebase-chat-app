package com.canhtv.ee.firebasechatapp.adapters

import android.view.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.models.UserData
import com.google.android.material.appbar.MaterialToolbar

class  ContactRecyclerViewAdapter(
    private val data: MutableList<UserData>,
    val onItemClickHandler: (UserData) -> Unit,
) : RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        val toolbar: MaterialToolbar = view.findViewById(R.id.contact_toolbar)

        init {
            itemView.setOnClickListener {
                onItemClicked(absoluteAdapterPosition)
            }

            toolbar.overflowIcon = AppCompatResources.getDrawable(toolbar.context,R.drawable.ic_overflow_action)
            toolbar.setOnMenuItemClickListener { menu ->

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

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.contact_item_view, viewGroup, false)
        return ViewHolder(view) {
            onItemClickHandler(data[it])
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.toolbar.title = data[position].username
    }

    override fun getItemCount() = data.size

}
