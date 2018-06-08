package xyz.madki.trail.ui.newslist

import android.arch.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import xyz.madki.trail.api.NewsApi
import xyz.madki.trail.db.NewsDao
import xyz.madki.trail.models.News
import javax.inject.Inject

class NewsListViewModel @Inject constructor(private val newsDao: NewsDao, private val api: NewsApi) : ViewModel() {

    fun getNewsList(): Flowable<List<News>> {
        return this.newsDao.load()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun reload() {
        this.api.getNews().subscribeOn(Schedulers.io())
                .subscribe( {
                    this.newsDao.insertAll(it.items.result.map { it.toNews() })
                }, {
                    Timber.e(it)
                })
    }
}