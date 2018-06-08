package xyz.madki.trail.db

import android.arch.persistence.room.*
import io.reactivex.Flowable
import xyz.madki.trail.models.News

@Dao
abstract class NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg news: News)

    @Query("SELECT * FROM news ORDER BY publishedAt DESC")
    abstract fun load(): Flowable<List<News>>

    @Transaction
    open fun insertAll(newsList: List<News>) {
        newsList.forEach {
            insert(it)
        }
    }
}