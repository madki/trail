package xyz.madki.trail.models

import com.google.gson.annotations.SerializedName
import timber.log.Timber


data class ApiResult(
    val items: Items
)

data class Items(
        val result: List<NewsResult>
)

data class NewsResult(
        val uuid: String,
        val title: String,
        val type: String,
        val summary: String,
        val content: String,
        @SerializedName("published_at") val publishedAt: Long,
        @SerializedName("main_image") val image: Image
) {
    fun toNews(): News {
        Timber.d(this.toString())
        return News(
                uuid,
                title,
                type,
                summary,
                content,
                publishedAt,
                image.url,
                image.getScaledImage()
        )
    }
}

data class Image(
    @SerializedName("original_url") val url: String,
    @SerializedName("original_height") val height: Int,
    @SerializedName("original_width") val width: Int,
    val resolutions: List<Resolution>
) {
    fun getScaledImage(): String {
        return resolutions.first { it.tag == "fit-width-640" }.url
    }
}

data class Resolution(
        val url: String,
        val height: Int,
        val width: Int,
        val tag: String
)