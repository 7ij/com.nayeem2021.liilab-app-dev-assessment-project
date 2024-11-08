package com.nayeem2021.liilab_app_dev_assesment_project.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nayeem2021.liilab_app_dev_assesment_project.R
import com.nayeem2021.liilab_app_dev_assesment_project.presentation.model.StoryModel
import com.nayeem2021.liilab_app_dev_assesment_project.presentation.model.StoryModelSelf

class HomePageStoriesRvAdapter(val dataSet: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val position = viewType
        when(dataSet[position]) {
            is StoryModelSelf -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.home_page_recycler_view_story_item_self_layout, parent, false
                )
                return HomePageStoriesViewHolderSelf(view)
            }
            is StoryModel -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.home_page_recycler_view_story_item_friend_layout, parent, false
                )
                return HomePageStoriesViewHolder(view)
            }
            else -> {
                throw IllegalStateException("Unknown view type for story recycler view.")
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(dataSet[position]) {
            is StoryModelSelf -> {
                holder as HomePageStoriesViewHolderSelf
                holder.bind(dataSet[position] as StoryModelSelf)
            }
            is StoryModel -> {
                holder as HomePageStoriesViewHolder
                holder.bind(dataSet[position] as StoryModel)
            }
        }
    }

    class HomePageStoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val storyUserTextView = itemView.findViewById<TextView>(R.id.story_user)
        val storyImageView = itemView.findViewById<ImageView>(R.id.story_user_photo)
        fun bind(storyModel: StoryModel) {
            storyUserTextView.text = storyModel.user
            storyImageView.setImageResource(storyModel.userPhoto)
        }

    }
    class HomePageStoriesViewHolderSelf(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val storyUserTextView = itemView.findViewById<TextView>(R.id.story_user)
        val storyImageView = itemView.findViewById<ImageView>(R.id.story_user_photo)
        // for now it's looking identical but it should be different later
        fun bind(storyModel: StoryModelSelf) {
            storyUserTextView.text = storyModel.user
            storyImageView.setImageResource(storyModel.userPhoto)
        }
    }
}
