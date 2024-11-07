package com.nayeem2021.liilab_app_dev_assesment_project

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.Flow
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class HomePageRecyclerViewAdapter(
    private val dataSet: List<Any>,
    private val fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.i("lolita", "view type: ${dataSet[viewType].javaClass.simpleName}")
        when (dataSet[viewType]) {
            is HomePageStoriesModel -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.home_page_stories, parent, false
                )
                val rv = view.findViewById<RecyclerView>(R.id.home_page_stories_recycler_view)
                rv.adapter = HomePageStoriesRecyclerViewAdapter()
                return HomePageViewHolder(view)
            }

            is CreatePostModel -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.home_page_create_post, parent, false
                )
                view.findViewById<View>(R.id.whats_happening_button).setOnClickListener {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, CreatePostFragment()).commit()
                }
                return HomePageViewHolder(view)
            }

            is BirthdayModel -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.home_page_birthday_section, parent, false
                )
                return HomePageViewHolder(view)
            }

            is HomePageRecentEventModel -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.home_page_recent_event_listing_section, parent, false
                )
                val rv = view.findViewById<RecyclerView>(R.id.recent_event_recycler_view)
                val model = dataSet[viewType] as HomePageRecentEventModel
                val dataSet: List<SingleRecentEventModel> = model.events
                rv.adapter = HomePageRecentEventRecyclerViewAdapter(dataSet)
                rv.addItemDecoration(PaddingInBetweenRecyclerViewDecorator(14))
                return HomePageViewHolder(view)
            }

            is PostModel -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.home_page_post,
                    parent, false
                )
                val flow = view.findViewById<Flow>(R.id.post_flow_layout)

                val postModel = dataSet[viewType] as PostModel

                Log.i("lolita", "flow layout: $flow")
                val numOfItem = flow.referencedIds.size
                Log.i("lolita", "number of item: $numOfItem")

                when (numOfItem) {
                    1 -> {

                    }

                    2 -> {

                    }

                    3 -> {
                        with(flow) {
                            setMaxElementsWrap(2)
                            setWrapMode(Flow.WRAP_CHAIN)
                            setOrientation(Flow.HORIZONTAL)

                        }
                    }

                    4 -> {

                    }

                    else -> {
                        with(flow) {
                            setMaxElementsWrap(2)
                            setWrapMode(Flow.WRAP_CHAIN)
                            setOrientation(Flow.HORIZONTAL)
                        }
                    }
                }
                // comment section
                val comments = postModel.comments
                if (comments.isNotEmpty()) {
                    val commentRv = view.findViewById<RecyclerView>(R.id.rvCommentSection)
                    commentRv.adapter = CommentRecyclerViewAdapter(comments)
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
