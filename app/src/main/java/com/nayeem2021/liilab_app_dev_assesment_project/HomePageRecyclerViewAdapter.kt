package com.nayeem2021.liilab_app_dev_assesment_project

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class HomePageRecyclerViewAdapter(
    private val dataSet: List<Any>,
    private val navigateToCreatePostFragment : () -> Unit
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
                return StoriesViewHolder(view)
            }

            is CreatePostModel -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.home_page_create_post, parent, false
                )
                view.findViewById<View>(R.id.whats_happening_button).setOnClickListener {
                    navigateToCreatePostFragment()
                }
                return CreatePostViewHolder(view)
            }

            is BirthdayModel -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.home_page_birthday_section, parent, false
                )
                return BirthdayViewHolder(view)
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
                return RecentEventViewHolder(view)
            }

            is PostModel -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.home_page_post,
                    parent, false
                )

                return PostViewHolder(view)
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
        when (dataSet[position]) {
            is HomePageStoriesModel -> {
                holder as StoriesViewHolder
                holder.bind(dataSet[position] as HomePageStoriesModel)
            }

            is CreatePostModel -> {

            }

            is BirthdayModel -> {

            }

            is HomePageRecentEventModel -> {
            }

            is PostModel -> {
                holder as PostViewHolder
                holder.bind(dataSet[position] as PostModel)
            }

            else -> {
                throw IllegalArgumentException("Unknown type of layout in home page recycler view")
            }
        }
    }

    class CreatePostViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
    class StoriesViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val rv = itemView.findViewById<RecyclerView>(R.id.home_page_stories_recycler_view)
        init {
            // we need to do this only once
            // otherwise each time we'll scroll back to the top
            // 18dp space will be added to each item
            // I'm not sure yet if recyclerViewAdapter is also needed to be initialized only once

            rv.addItemDecoration(PaddingInBetweenRecyclerViewDecorator(18, true))
        }
        fun bind(model: HomePageStoriesModel) {
            rv.adapter = HomePageStoriesRvAdapter(model.stories)
        }
    }


    class PostViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUserName = itemView.findViewById<TextView>(R.id.home_page_post_user_name)
        val tvPostTime = itemView.findViewById<TextView>(R.id.home_page_post_time)
        val tvPostPrivacy = itemView.findViewById<TextView>(R.id.home_page_post_privacy)
        val tvPostConent = itemView.findViewById<TextView>(R.id.home_page_post_main_content)
        val btnCommentCount = itemView.findViewById<TextView>(R.id.comment_count_button)
        val btnShareCount = itemView.findViewById<TextView>(R.id.share_count_button)
        val flow = itemView.findViewById<Flow>(R.id.post_flow_layout)
        val imageGridConstraintLayout = itemView.findViewById<ConstraintLayout>(R.id.home_page_post_image_grid)

        fun bind(model: PostModel) {
            tvUserName.text = model.user
            tvPostTime.text = model.postTime
            tvPostPrivacy.text = model.postPrivacy
            tvPostConent.text = model.postContent
            btnCommentCount.text = model.commentsCount.toString() + " Comments"
            btnShareCount.text = model.sharesCount.toString() + " Shares"

            Log.i("lolita", "flow layout: $flow")
            val numOfItem = model.postImages.size
            Log.i("lolita", "number of item: $numOfItem")

            flow.referencedIds = model.postImages.map {
                val imageView = ImageView(itemView.context).apply {
                    id = View.generateViewId()
                    setImageResource(it)
                    layoutParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                        ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                    )
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }
                Log.i("lolita", "image view id: ${imageView.id}")
                Log.i("lolita", "image parent: ${imageGridConstraintLayout}")
                imageGridConstraintLayout.addView(imageView)
                imageView.id
            }.toIntArray()

            with(flow) {
                setMaxElementsWrap(2)
                setWrapMode(Flow.WRAP_CHAIN)
                setOrientation(Flow.HORIZONTAL)
            }
            if(numOfItem == 3 || numOfItem >= 5) {
                flow.setMaxElementsWrap(3)
            }

            // comment section
            val comments = model.comments
            if (comments.isNotEmpty()) {
                val commentRv = itemView.findViewById<RecyclerView>(R.id.rvCommentSection)
                commentRv.adapter = CommentRecyclerViewAdapter(comments)
                commentRv.addItemDecoration(PaddingInBetweenRecyclerViewDecorator(14))
            }
        }
    }

    class BirthdayViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class RecentEventViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
