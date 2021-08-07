package com.canhtv.ee.firebasechatapp.adapters

import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.model.User
import com.google.android.material.appbar.MaterialToolbar

class  ConversationRecyclerViewAdapter(
    private val data: List<User>,
    val onItemClickHandler: (User) -> Unit,
) : RecyclerView.Adapter<ConversationRecyclerViewAdapter.ViewHolder>() {

    private var imageBindingAdapter = ImageBindingAdapter()

    class ViewHolder(view: View, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(view) {

        val collapseView: ConstraintLayout = view.findViewById(R.id.conversation_iv_collapse)
//        val collapseImageView: ImageView = view.findViewById(R.id.conversation_iv_avatar_image_view)
        val collapseTextView: TextView = view.findViewById(R.id.conversation_iv_text_view)
        val collapseBtn: Button = view.findViewById(R.id.conversation_iv_button)

        val expandView: ConstraintLayout = view.findViewById(R.id.conversation_iv_expand)
//        val expandImageView: ImageView = view.findViewById(R.id.conversation_ivx_avatar_image_view)
        val expandTextView: TextView = view.findViewById(R.id.conversation_ivx_text_view)
        val expandBtn: Button = view.findViewById(R.id.conversation_ivx_button)
        val expandToolbar: MaterialToolbar = view.findViewById(R.id.conversation_ivx_toolbar)

        init {
            itemView.setOnClickListener {
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
                if (menu.itemId == R.id.ic_full_screen) {
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

        /*imageBindingAdapter.bindImage(
            viewHolder.collapseImageView.context,
            viewHolder.collapseImageView,
            data[position].urlToImage!!
        )
        imageBindingAdapter.bindImage(
            viewHolder.expandImageView.context,
            viewHolder.expandImageView,
            data[position].urlToImage!!
        )*/

    }

    override fun getItemCount() = data.size

}
