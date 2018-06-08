package xyz.madki.trail.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Todo (
        val title: String,
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,
        val count: Int = 0
) {
    fun incrementCount(): Todo {
        return this.copy(count = this.count + 1)
    }
}