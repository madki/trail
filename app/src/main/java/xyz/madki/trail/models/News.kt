package xyz.madki.trail.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.joda.time.format.DateTimeFormat

@Entity
data class News (
        @PrimaryKey
        val uuid: String,
        val title: String,
        val type: String,
        val summary: String?,
        val content: String?,
        val publishedAt: Long,
        val originalImage: String,
        val scaledImage: String
) {
        fun getFormattedDate(): String {
            return DATE_TIME_FORMMATER.print(publishedAt)
        }

    companion object {
        val DATE_TIME_FORMMATER = DateTimeFormat.forPattern("DD/MM/YYYY hh:mm a")
    }
}