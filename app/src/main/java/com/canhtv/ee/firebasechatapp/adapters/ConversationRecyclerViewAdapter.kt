package com.canhtv.ee.firebasechatapp.adapters

import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.models.UserData
import com.google.android.material.appbar.MaterialToolbar

class  ConversationRecyclerViewAdapter(
    private val data: MutableList<UserData>,
    val onItemClickHandler: (UserData) -> Unit,
) : RecyclerView.Adapter<ConversationRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(view) {

        val collapseView: ConstraintLayout = view.findViewById(R.id.conversation_iv_collapse)
        val collapseTextView: TextView = view.findViewById(R.id.conversation_iv_message_text)
        val collapseBtn: AppCompatImageButton = view.findViewById(R.id.conversation_iv_button)

        val expandView: ConstraintLayout = view.findViewById(R.id.conversation_iv_expand)
        val expandTextView: TextView = view.findViewById(R.id.conversation_ivx_message_text)
        val expandBtn: AppCompatImageButton = view.findViewById(R.id.conversation_ivx_button)
        val expandToolbar: MaterialToolbar = view.findViewById(R.id.conversation_ivx_toolbar)

        init {
            collapseView.setOnClickListener {
                onItemClicked(absoluteAdapterPosition)
            }

            collapseBtn.setOnClickListener {
                collapseView.visibility = View.GONE
                expandView.visibility = View.VISIBLE
            }

            expandBtn.setOnClickListener {
                expandView.visibility = View.GONE
                collapseView.visibility = View.VISIBLE
            }

            expandToolbar.setOnMenuItemClickListener { menu ->
                if (menu.itemId == R.id.ic_full_screen_conversation) {
                    onItemClicked(absoluteAdapterPosition)
                }
                return@setOnMenuItemClickListener true
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.conversation_item_view, viewGroup, false)
        return ViewHolder(view) {
            onItemClickHandler(data[it])
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.collapseTextView.text = data[position].message
        viewHolder.expandTextView.text = data[position].message
    }

    override fun getItemCount() = data.size

}
