package xyz.madki.trail.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xyz.madki.trail.ui.todolist.TodoListFragment

@Module
abstract class FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun todoListFragment(): TodoListFragment
}