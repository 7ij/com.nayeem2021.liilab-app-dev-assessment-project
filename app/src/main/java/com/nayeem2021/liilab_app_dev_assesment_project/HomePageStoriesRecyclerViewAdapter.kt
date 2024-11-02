package com.nayeem2021.liilab_app_dev_assesment_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HomePageStoriesRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.home_page_recycler_view_story_item_layout, parent, false
        )
        return HomePageStoriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    class HomePageStoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
