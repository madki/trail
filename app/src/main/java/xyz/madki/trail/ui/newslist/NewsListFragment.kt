package xyz.madki.trail.ui.newslist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_news_list.*
import timber.log.Timber
import xyz.madki.trail.R
import xyz.madki.trail.ui.newsdetail.NewsDetailFragment
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class NewsListFragment : DaggerFragment() {
    @Inject lateinit var vm: NewsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.reload()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = NewsListAdapter {
            // TODO open next page
            Timber.d("Clicked news")
            activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment, NewsDetailFragment.newInstance(it))
                    .addToBackStack("NEWS_DETAIL_FRAGMENT")
                    .commit()
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        vm.getNewsList().subscribe { adapter.setNewsList(it) }
    }
}
