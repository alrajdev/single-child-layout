package dev.alraj.singlelayoutexample

import android.app.Application
import android.text.style.TtsSpan
import timber.log.Timber

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(object: Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, "single_alraj_$tag", message, t)
            }
        })
    }
}