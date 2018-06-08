package xyz.madki.trail.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import xyz.madki.trail.models.Todo

@Database(
        entities = [
            Todo::class
        ],
        version = 1,
        exportSchema = false
)
abstract class AppDb: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}