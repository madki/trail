package xyz.madki.trail.ui.newslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news.view.*
import xyz.madki.trail.R
import xyz.madki.trail.models.News

class NewsListAdapter(
        private var newsList: List<News> = ArrayList(),
        private val onClick: (news: News) -> Unit
        ): RecyclerView.Adapter<NewsListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListHolder {
        return NewsListHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsListHolder, position: Int) {
        holder.bind(newsList[position]) {
            onClick(newsList[position])
        }
    }

    fun setNewsList(newsList: List<News>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }
}

class NewsListHolder(private val v: View): RecyclerView.ViewHolder(v) {
    fun bind(news: News, listener: () -> Unit) {
        with (v) {
            tv_title.text = news.title
            tv_date.text = news.getFormattedDate()
            Glide.with(this).load(news.scaledImage).into(iv_news_header)
            setOnClickListener {
                listener()
            }
        }
    }
}