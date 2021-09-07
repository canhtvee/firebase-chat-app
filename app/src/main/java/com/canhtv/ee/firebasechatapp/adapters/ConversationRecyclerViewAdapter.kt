package com.canhtv.ee.firebasechatapp.adapters

import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.LayoutInflaterCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.models.UserData
import com.canhtv.ee.firebasechatapp.databinding.ConversationItemViewCollapseBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.transition.MaterialContainerTransform

class  ConversationRecyclerViewAdapter(
    private val data: MutableList<UserData>,
    val onItemClickHandler: (UserData) -> Unit,
) : RecyclerView.Adapter<ConversationRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(view) {

        private val conversationItemView: LinearLayoutCompat = view.findViewById(R.id.conversation_iv)

        private val collapseView: ConstraintLayout = view.findViewById(R.id.conversation_iv_collapse)
        val collapseTextView: TextView = view.findViewById(R.id.conversation_iv_message_text)
        private val collapseBtn: AppCompatImageButton = view.findViewById(R.id.conversation_iv_button)

        private val expandView: ConstraintLayout = view.findViewById(R.id.conversation_iv_expand)
        val expandTextView: TextView = view.findViewById(R.id.conversation_ivx_message_text)
        private val expandBtn: AppCompatImageButton = view.findViewById(R.id.conversation_ivx_button)
        private val expandToolbar: MaterialToolbar = view.findViewById(R.id.conversation_ivx_toolbar)

        init {
            collapseView.setOnClickListener {
                onItemClicked(absoluteAdapterPosition)
            }

            collapseBtn.setOnClickListener {
                transition(collapseView, expandView, conversationItemView as ViewGroup)

            }

            expandBtn.setOnClickListener {
                transition(expandView, collapseView, conversationItemView as ViewGroup)
            }

            expandToolbar.setOnMenuItemClickListener { menu ->
                if (menu.itemId == R.id.ic_full_screen_conversation) {
                    onItemClicked(absoluteAdapterPosition)
                }
                return@setOnMenuItemClickListener true
            }
        }

        private fun transition(startV: View, endV: View, viewGroup: ViewGroup) {
            startV.visibility = View.GONE
            endV.visibility = View.VISIBLE
            val transform = MaterialContainerTransform().apply {
                startView = startV
                endView = endV
                scrimColor = Color.Transparent.toArgb()
            }
            TransitionManager.beginDelayedTransition(viewGroup, transform)
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


