package com.nayeem2021.liilab_app_dev_assesment_project.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nayeem2021.liilab_app_dev_assesment_project.domain.PaddingInBetweenRecyclerViewDecorator
import com.nayeem2021.liilab_app_dev_assesment_project.R
import com.nayeem2021.liilab_app_dev_assesment_project.presentation.model.CommentModel
import com.nayeem2021.liilab_app_dev_assesment_project.presentation.model.ReplyAdapter
import com.nayeem2021.liilab_app_dev_assesment_project.presentation.model.ReplyModel

class CommentRecyclerViewAdapter(val dataSet: List<CommentModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.home_page_post_comment_recycler_view_item_layout, parent,
                false
            )
        val replyRv =
            itemView.findViewById<RecyclerView>(R.id.home_page_post_comment_reply_recycler_view)
        val dataSet: List<ReplyModel> = dataSet[viewType].replies
        if (dataSet.isNotEmpty()) {
            replyRv.adapter = ReplyAdapter(dataSet)
            replyRv.addItemDecoration(PaddingInBetweenRecyclerViewDecorator(14))
        }
        return CommentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as CommentViewHolder
        holder.bind(dataSet[position])
    }

    inner class CommentViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userTv = itemView.findViewById<TextView>(R.id.comment_profile_name)
        val whenTv = itemView.findViewById<TextView>(R.id.when_comment_was_posted)
        val contentTv = itemView.findViewById<TextView>(R.id.comment_content)

        fun bind(commentModel: CommentModel) {
            userTv.text = commentModel.user
            whenTv.text = commentModel.whenPosted
            contentTv.text = commentModel.content
        }
    }
}
