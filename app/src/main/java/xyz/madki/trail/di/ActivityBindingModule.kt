package xyz.madki.trail.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xyz.madki.trail.ui.MainActivity

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    abstract fun mainActivity(): MainActivity
}