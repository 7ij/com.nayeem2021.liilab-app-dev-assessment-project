package com.nayeem2021.liilab_app_dev_assesment_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

enum class SectionType {
    RECENT_EVENT_SECTION
}

class HomePageRecyclerViewAdapter(
    private val dataSet: List<Any>,
    private val navigateToCreatePostFragment : () -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.home_page_stories, parent, false
                )
                val rv = view.findViewById<RecyclerView>(R.id.home_page_stories_recycler_view)
                rv.adapter = HomePageStoriesRecyclerViewAdapter()
                return HomePageViewHolder(view)
            }

            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.home_page_create_post, parent, false
                )
                view.findViewById<View>(R.id.whats_happening_button).setOnClickListener {
                    navigateToCreatePostFragment()
                }
                return HomePageViewHolder(view)
            }

            2 -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.home_page_birthday_section, parent, false
                )
                return HomePageViewHolder(view)
            }

            3 -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.home_page_recent_event_listing_section, parent, false
                )
                val rv = view.findViewById<RecyclerView>(R.id.recent_event_recycler_view)
                val dataSet: List<HomePageRecentEventDataModel> = listOf(
                    HomePageRecentEventDataModel(
                        "Graduation Ceremony",
                        "The graduation ceremony is also sometimes called. The graduation " +
                                "ceremony is also sometimes called...",
                        8
                    ),
                    HomePageRecentEventDataModel(
                        "Photography Ideas",
                        "Reflections. Reflections work because they can create...",
                        11
                    )
                )
                rv.adapter = HomePageRecentEventRecyclerViewAdapter(dataSet)
                rv.addItemDecoration(PaddingInBetweenRecyclerViewDecorator(14))
                return HomePageViewHolder(view)
            }

            4 -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.home_page_post,
                    parent, false
                )
                val commentDataSet = dataSet[4] as List<CommentModel>
                if(commentDataSet.isNotEmpty()) {
                    val commentRv = view.findViewById<RecyclerView>(R.id.rvCommentSection)
                    commentRv.adapter = CommentRecyclerViewAdapter(commentDataSet)
                }
                return HomePageViewHolder(view)
            }

            else -> {
                throw IllegalArgumentException("Unknown type of layout in home page recycler view")
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    class HomePageViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}
