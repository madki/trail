package xyz.madki.trail.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import xyz.madki.trail.models.News

@Database(
        entities = [
            News::class
        ],
        version = 1,
        exportSchema = false
)
abstract class AppDb: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}