package xyz.madki.trail.ui.newsdetail

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_news_detail.*
import xyz.madki.trail.R
import xyz.madki.trail.models.News


class NewsDetailFragment: DaggerFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val news: News = arguments!!.getParcelable(NEWS_ARG_KEY) as News
        tv_title.text = news.title
        tv_detail_date.text = news.getFormattedDate()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tv_content.text = Html.fromHtml(news.content ?: news.summary ,Html.FROM_HTML_MODE_LEGACY)
        } else {
            tv_content.text = Html.fromHtml(news.content ?: news.summary)
        }
        Glide.with(this).load(news.originalImage).into(iv_detail_header)
    }

    companion object {
        val NEWS_ARG_KEY = "NEWS_ARG_KEY"

        fun newInstance(news: News): NewsDetailFragment {
            val fragment = NewsDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(NEWS_ARG_KEY, news)
            fragment.arguments = bundle

            return fragment
        }
    }
}