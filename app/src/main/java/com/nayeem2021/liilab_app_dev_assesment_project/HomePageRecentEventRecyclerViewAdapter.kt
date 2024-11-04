package com.nayeem2021.liilab_app_dev_assesment_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomePageRecentEventRecyclerViewAdapter(private val dataSet: List<HomePageRecentEventDataModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.home_page_recent_event_listing_item,
            parent, false
        )
        return HomePageRecentEventViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as HomePageRecentEventViewHolder
        with(holder) {
            eventName.text = dataSet[position].name
            eventContent.text = dataSet[position].content
            viewCount.text = dataSet[position].viewCount.toString()
        }
    }

    class HomePageRecentEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var eventName = itemView.findViewById<TextView>(R.id.home_page_recent_event_name)
        var eventContent = itemView.findViewById<TextView>(R.id.home_page_recent_event_content)
        var viewCount = itemView.findViewById<TextView>(R.id.home_page_recent_event_view_count)
    }
}
