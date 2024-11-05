package com.nayeem2021.liilab_app_dev_assesment_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    class ReplyViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(replyModel: ReplyModel) {
        }
    }
}
