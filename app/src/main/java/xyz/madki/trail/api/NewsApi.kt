package xyz.madki.trail.api

import io.reactivex.Flowable
import retrofit2.http.GET
import xyz.madki.trail.models.ApiResult


interface NewsApi {
    @GET("newsfeed")
    fun getNews(): Flowable<ApiResult>
}