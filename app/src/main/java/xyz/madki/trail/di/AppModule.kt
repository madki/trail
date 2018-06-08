package xyz.madki.trail.di

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import xyz.madki.trail.App
import xyz.madki.trail.api.NewsApi
import xyz.madki.trail.db.AppDb
import xyz.madki.trail.db.NewsDao
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideAppDb(app: App): AppDb {
        return Room
                .databaseBuilder(app, AppDb::class.java, "app.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(db: AppDb): NewsDao {
        return db.newsDao()
    }

    @Singleton
    @Provides
    fun provideNewsApi(): NewsApi {
        val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor())
                .build()
        return Retrofit.Builder()
                .baseUrl("https://mobile-homerun-yql.media.yahoo.com/v2/homerun/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NewsApi::class.java)
    }
}