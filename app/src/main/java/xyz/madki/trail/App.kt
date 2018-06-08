package xyz.madki.trail

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber
import xyz.madki.trail.di.DaggerAppComponent


class App: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}