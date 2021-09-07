package com.canhtv.ee.firebasechatapp.adapters

import android.content.Context
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
import com.canhtv.ee.firebasechatapp.databinding.ConversationItemViewBinding
import com.canhtv.ee.firebasechatapp.databinding.ConversationItemViewCollapseBinding
import com.canhtv.ee.firebasechatapp.databinding.ConversationItemViewExpandBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.transition.MaterialContainerTransform

class  ConversationRecyclerViewAdapter(
    private val data: MutableList<UserData>,
    val onItemClickHandler: (UserData) -> Unit,
) : RecyclerView.Adapter<ConversationRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(
        binding: ConversationItemViewBinding,
        onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        private val conversationItemView = binding.root
        val collapseBinding = binding.idConversationItemViewCollapse
        val expandBinding = binding.idConversationItemViewExpand

        init {
            collapseBinding.root.setOnClickListener {
                onItemClicked(absoluteAdapterPosition)
            }

            collapseBinding.conversationIvButton.setOnClickListener {
                transition(collapseBinding.root, expandBinding.root, conversationItemView as ViewGroup)
            }

            expandBinding.conversationIvxButton.setOnClickListener {
                transition(expandBinding.root, collapseBinding.root, conversationItemView as ViewGroup)
            }

            expandBinding.conversationIvxToolbar.setOnMenuItemClickListener { menu ->
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
        val binding = ConversationItemViewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder( binding) { onItemClickHandler(data[it]) }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.collapseBinding.conversationIvMessageText.text = data[position].message
        viewHolder.expandBinding.conversationIvxMessageText.text = data[position].message
    }

    override fun getItemCount() = data.size

}


