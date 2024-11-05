package com.nayeem2021.liilab_app_dev_assesment_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReplyAdapter(val dataSet: List<ReplyModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.home_page_post_comment_reply_recycler_view_item,
            parent,
            false
        )
        return ReplyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ReplyViewHolder
        holder.bind(dataSet[position])
    }

    class ReplyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userWhoIsReplyingTextView: TextView =
            itemView.findViewById(R.id.user_who_is_replying)
        private val whenReplyWasPostedTextView =
            itemView.findViewById<TextView>(R.id.when_reply_was_posted)
        private val userWhoIsBeingRepliedTextView =
            itemView.findViewById<TextView>(R.id.user_who_is_being_replied)
        private val replyContentTextView: TextView = itemView.findViewById(R.id.reply_content)
        private val replyingToContentTextView =
            itemView.findViewById<TextView>(R.id.comment_on_which_reply_was_given)

        fun bind(replyModel: ReplyModel) {
            userWhoIsReplyingTextView.text = replyModel.user
            userWhoIsBeingRepliedTextView.text = replyModel.replyingTo
            whenReplyWasPostedTextView.text = replyModel.whenReplied
            replyContentTextView.text = replyModel.replyMsg
            replyingToContentTextView.text = replyModel.replyingToContent
        }
    }
}
