package xyz.madki.trail.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import xyz.madki.trail.App
import javax.inject.Singleton



@Singleton
@Component(modules = [
    ActivityBindingModule::class,
    AppModule::class,
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent: AndroidInjector<App> {
    override fun inject(app: App)
    fun app(): App

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}