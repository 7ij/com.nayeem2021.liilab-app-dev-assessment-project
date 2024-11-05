package com.nayeem2021.liilab_app_dev_assesment_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

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
        val replyRv = itemView.findViewById<RecyclerView>(R.id.home_page_post_comment_reply_recycler_view)
        val dataSet:List<ReplyModel> = dataSet[viewType].replies
        replyRv.adapter = ReplyAdapter(dataSet)
        return CommentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
