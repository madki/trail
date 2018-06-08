package xyz.madki.trail.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import xyz.madki.trail.models.Todo

@Dao
abstract class TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg todos: Todo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun createTodoIfNotExists(todo: Todo): Long

    @Query("SELECT * FROM todo ORDER BY id")
    abstract fun load(): Flowable<List<Todo>>
}