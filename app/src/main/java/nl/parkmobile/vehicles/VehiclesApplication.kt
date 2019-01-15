package nl.parkmobile.vehicles

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import nl.parkmobile.vehicles.injection.AppInjector
import javax.inject.Inject

open class VehiclesApplication : Application(), HasActivityInjector {

    companion object {
        lateinit var instance: VehiclesApplication private set

        fun getContext(): VehiclesApplication {
            return instance
        }
    }
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        instance = this
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }
}