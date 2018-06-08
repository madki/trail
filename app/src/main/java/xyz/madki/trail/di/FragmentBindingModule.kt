package xyz.madki.trail.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xyz.madki.trail.ui.newslist.NewsListFragment

@Module
abstract class FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun newsListFragment(): NewsListFragment
}