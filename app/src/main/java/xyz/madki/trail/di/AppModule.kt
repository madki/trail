package xyz.madki.trail.di

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import xyz.madki.trail.App
import xyz.madki.trail.db.AppDb
import xyz.madki.trail.db.TodoDao
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
    fun provideTodoDao(db: AppDb): TodoDao {
        return db.todoDao()
    }
}